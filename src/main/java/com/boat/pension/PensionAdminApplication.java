package com.boat.pension;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.boat.pension.*"})
//@MapperScan("com.boat.pension.mapper")

public class PensionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PensionAdminApplication.class, args);
    }

}
