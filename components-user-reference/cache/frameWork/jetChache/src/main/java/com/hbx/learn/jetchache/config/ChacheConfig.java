package com.hbx.learn.jetchache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMethodCache(basePackages = "com.hbx.learn")
public class ChacheConfig {
}
