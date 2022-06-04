package com.prototype.framework.configuration.jdbc.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import java.util.*

class DynamicRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return MultiDataSourceContextHolder.dataSourceType ?: resolvedDefaultDataSource
    }
}