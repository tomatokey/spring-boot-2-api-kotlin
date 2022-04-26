package com.tomatokey.prototype.configuration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (Objects.isNull(MultiDataSourceContextHolder.getDataSourceType())) {
            return DataSourceType.REF;
        }
        return MultiDataSourceContextHolder.getDataSourceType();
    }

}
