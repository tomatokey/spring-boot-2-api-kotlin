package com.prototype.framework.configuration.jdbc;

import com.prototype.framework.configuration.jdbc.datasource.DataSourceType;
import com.prototype.framework.configuration.jdbc.datasource.DynamicRoutingDataSource;
import com.prototype.framework.configuration.jdbc.converter.JdbcSingleValueObjectConverter;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * JDBC用の設定クラス
 */
@Configuration
@EnableJdbcAuditing // @CreatedDateや@LastModifiedDateを使用するために付与
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(
                new JdbcSingleValueObjectConverter()
        );
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        // コミット時にエラーが発生した場合、ロールバックを行うように設定
        // コネクションプールを使用しているため、コミットフェーズでエラーになったコネクションが別の処理で使われ、失敗した操作が一緒にコミットされることを防ぐため
        transactionManager.setRollbackOnCommitFailure(true);
        return transactionManager;
    }

    @Bean
    @Primary
    public DataSource dynamicRoutingDataSource() {
        final DataSource dataSourceUpd = dataSourceUpd();
        final DataSource dataSourceRef = dataSourceRef();
        final Map<Object, Object> targetDataSources = Map.of(
                DataSourceType.UPD, dataSourceUpd,
                DataSourceType.REF, dataSourceRef
        );

        final DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceRef);

        return dynamicRoutingDataSource;
    }

    /**
     * 更新用DataSource
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.upd")
    public DataSource dataSourceUpd() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 参照用DataSource
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ref")
    public DataSource dataSourceRef() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
