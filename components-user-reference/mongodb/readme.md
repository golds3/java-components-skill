# SpringBoot 整合MongoDB

注意，springboot的版本很重要，目前3.0.2版本启动正常

pom
JDK 17
Mongo server版本 7.x
```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    </dependencies>
```

配置文件
```properties
spring.data.mongodb.uri=mongodb://ip:port/dbname
# 如果有用户
#spring.data.mongodb.uri=mongodb://user:pawd@ip:port/dbname
#如果是集群：
#spring.data.mongodb.uri=mongodb://user:pwd@ip1:port1,ip2:port2/dbname
```