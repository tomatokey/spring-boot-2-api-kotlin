package com.prototype.framework.configuration.jdbc.datasource

object MultiDataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceType>()
    var dataSourceType: DataSourceType?
        get() = contextHolder.get()
        set(dataSourceType) {
            contextHolder.set(dataSourceType)
        }

    fun removeContextHolder() {
        contextHolder.remove()
    }
}