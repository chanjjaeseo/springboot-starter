package com.springboot.demo.config.ds;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleDataSource {

    private static Set<String> dataSources = new HashSet<>();

    public MultipleDataSource(List<String> dsList) {
        this.dataSources.addAll(dsList);
    }

    public static Set<String> getDataSources() {
        return dataSources;
    }

}
