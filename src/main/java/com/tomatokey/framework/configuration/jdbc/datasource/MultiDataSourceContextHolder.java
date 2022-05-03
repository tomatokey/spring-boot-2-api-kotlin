package com.tomatokey.framework.configuration.jdbc.datasource;

import java.util.Objects;

public class MultiDataSourceContextHolder {

    private static ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

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
