package com.auth.userserver.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class URLCombiner {
    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private String port;

    public String combinePathWithHost(String path){
        return "http://"+host+":"+port+path;
    }
}
