package com.prototype.framework.configuration.jdbc

import com.prototype.framework.configuration.jdbc.annotation.DataSourceRef
import com.prototype.framework.configuration.jdbc.annotation.DataSourceUpd
import com.prototype.framework.configuration.jdbc.converter.JdbcSingleValueObjectConverter
import com.prototype.framework.configuration.jdbc.datasource.DataSourceType
import com.prototype.framework.configuration.jdbc.datasource.DynamicRoutingDataSource
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

/**
 * JDBC用の設定クラス
 */
@Configuration
@EnableJdbcAuditing // @CreatedDateや@LastModifiedDateを使用するために付与
class JdbcConfiguration : AbstractJdbcConfiguration() {

    override fun userConverters(): List<*> {
        return genericConverters()
    }

    private fun genericConverters(): List<GenericConverter> {
        return listOf(
                JdbcSingleValueObjectConverter()
        )
    }

    @Bean
    fun conversionService(): ConversionService {
        return (DefaultConversionService.getSharedInstance() as DefaultConversionService).apply {
            genericConverters().forEach { addConverter(it) }
        }
    }

    @Bean(TRANSACTION_MANAGER)
    @Primary
    fun transactionManager(dataSource: DataSource): JdbcTransactionManager {
        val transactionManager = JdbcTransactionManager(dataSource)
        // コミット時にエラーが発生した場合、ロールバックを行うように設定
        // コネクションプールを使用しているため、コミットフェーズでエラーになったコネクションが別の処理で使われ、失敗した操作が一緒にコミットされることを防ぐため
        transactionManager.isRollbackOnCommitFailure = true
        return transactionManager
    }

    @Bean
    @Primary
    fun dynamicRoutingDataSource(): DataSource {
        val dataSourceUpd = dataSourceUpd()
        val dataSourceRef = dataSourceRef()
        val targetDataSources = mapOf<Any, Any>(
                DataSourceType.UPD to dataSourceUpd,
                DataSourceType.REF to dataSourceRef
        )
        val dynamicRoutingDataSource = DynamicRoutingDataSource()
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources)
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceRef)
        return dynamicRoutingDataSource
    }

    /**
     * 更新用DataSource
     * @return
     */
    @Bean(DATA_SOURCE_UPD)
    @DataSourceUpd
    @ConfigurationProperties(prefix = "spring.datasource.upd")
    fun dataSourceUpd(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    /**
     * 参照用DataSource
     * @return
     */
    @Bean(DATA_SOURCE_REF)
    @DataSourceRef
    @ConfigurationProperties(prefix = "spring.datasource.ref")
    fun dataSourceRef(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    fun namedParameterJdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    companion object {
        const val DATA_SOURCE_UPD = "dataSourceUpd"
        const val DATA_SOURCE_REF = "dataSourceRef"
        const val TRANSACTION_MANAGER = "transactionManager"
    }

}