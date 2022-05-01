package com.tomatokey.framework.configuration.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (Objects.isNull(MultiDataSourceContextHolder.getDataSourceType())) {
            return getResolvedDefaultDataSource();
        }
        return MultiDataSourceContextHolder.getDataSourceType();
    }

}
