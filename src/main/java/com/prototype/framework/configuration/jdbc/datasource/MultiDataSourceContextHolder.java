package com.prototype.framework.configuration.jdbc.datasource;

import java.util.Objects;

public class MultiDataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType dataSourceType) {
        Objects.requireNonNull(dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }

    public static void removeContextHolder() {
        contextHolder.remove();
    }

}
