package com.springboot.demo.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public RoutingDataSource routingDataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(generateDataSources());
        routingDataSource.setDefaultTargetDataSource(dataSource1());
        return routingDataSource;
    }

    private Map<Object, Object> generateDataSources() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceNames.NORMAL, dataSource1());
        dataSourceMap.put(DataSourceNames.DS2, dataSource2());
        return dataSourceMap;
    }

    @Bean
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Bean
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Bean
    public MultipleDataSource multipleDataSource() {
        return new MultipleDataSource(Lists.newArrayList(
                DataSourceNames.NORMAL,
                DataSourceNames.DS2,
                DataSourceNames.DS3));
    }

}
