package com.tomatokey.prototype.configuration;

import com.tomatokey.prototype.configuration.converter.UserIdConverter;
import com.tomatokey.prototype.configuration.converter.UserNameConverter;
import com.tomatokey.prototype.configuration.datasource.DataSourceType;
import com.tomatokey.prototype.configuration.datasource.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@EnableJdbcAuditing // @CreatedDateや@LastModifiedDateを使用するために付与
@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(
                new UserIdConverter(),
                new UserNameConverter()
        );
    }

    @Bean
    @Primary
    public DataSource getDynamicRoutingDataSource() {
        // DBに反映するため、TransactionAwareDataSourceProxyでラッピング
        final DataSource dataSourceUpd = new TransactionAwareDataSourceProxy(getDataSourceUpd());
        final DataSource dataSourceRef = getDataSourceRef();
        final Map<Object, Object> targetDataSources = Map.of(
                DataSourceType.UPD, dataSourceUpd,
                DataSourceType.REF, dataSourceRef
        );

        final DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceUpd);

        return dynamicRoutingDataSource;
    }

    /**
     * 更新用DataSource
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.upd")
    public DataSource getDataSourceUpd() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 参照用DataSource
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ref")
    public DataSource getDataSourceRef() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
