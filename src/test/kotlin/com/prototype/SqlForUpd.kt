package com.prototype

import com.prototype.framework.configuration.jdbc.JdbcConfiguration
import org.springframework.core.annotation.AliasFor
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import java.lang.annotation.Inherited

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Sql
annotation class SqlForUpd(
    @get:AliasFor(annotation = Sql::class, attribute = "scripts")
    vararg val value: String = [],
    @get:AliasFor(annotation = Sql::class, attribute = "value")
    val scripts: Array<String> = [],
    @get:AliasFor(annotation = Sql::class, attribute = "config")
    val config: SqlConfig = SqlConfig(
        dataSource = JdbcConfiguration.DATA_SOURCE_UPD,
        transactionManager = PrototypeTestConfiguration.TRANSACTION_MANAGER
    )
)
