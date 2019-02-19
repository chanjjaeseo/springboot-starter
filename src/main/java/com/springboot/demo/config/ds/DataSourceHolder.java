package com.springboot.demo.config.ds;

public class DataSourceHolder {

    private static final ThreadLocal<String> currentDataSource = new ThreadLocal<>();

    public static void putDataSource(String dataSourceName) {
        currentDataSource.set(dataSourceName);
    }

    public static String getDataSource() {
        return currentDataSource.get();
    }

    public static void clear() {
        currentDataSource.remove();
    }

}
