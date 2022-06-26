package com.prototype

import com.prototype.framework.configuration.jdbc.annotation.DataSourceUpd
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.support.JdbcTransactionManager
import javax.sql.DataSource

@TestConfiguration
class PrototypeTestConfiguration {

    @Bean(TRANSACTION_MANAGER)
    fun transactionManager(@DataSourceUpd dataSource: DataSource): JdbcTransactionManager {
        val transactionManager = JdbcTransactionManager(dataSource)
        // コミット時にエラーが発生した場合、ロールバックを行うように設定
        // コネクションプールを使用しているため、コミットフェーズでエラーになったコネクションが別の処理で使われ、失敗した操作が一緒にコミットされることを防ぐため
        transactionManager.isRollbackOnCommitFailure = true
        return transactionManager
    }

    companion object {
        const val TRANSACTION_MANAGER = "transactionManagerForTest"
    }

}