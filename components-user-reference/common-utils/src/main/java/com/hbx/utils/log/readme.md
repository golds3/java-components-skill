# 最佳实践

Slf4j+Logback

pom
```xml 
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
    <version>1.4.2</version>
</dependency>
<!--include slf4j-api-1.7.21.jar and logback-core-1.0.13.jar  -->
```

spring环境下
spring 使用的日志解决方案是 common-logging + log4j。需要一个桥接 jar 包：logback-ext-spring。来重定向到Slf4j
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.1.3</version>
</dependency>
<dependency>
    <groupId>org.logback-extensions</groupId>
    <artifactId>logback-ext-spring</artifactId>
    <version>0.1.2</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
    <version>1.7.12</version>
</dependency>
```

logback配置文件logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--自定义颜色配置-->
    <conversionRule conversionWord="customcolor" converterClass="com.hbx.utils.log.configuration.LogbackColorful"/>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %magenta([%thread]) %customcolor(%-5level) %cyan(%logger{15}) - %msg%n
            </pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <file>log/output.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
            <fileNamePattern>log/output.log.%i</fileNamePattern>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>1MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

