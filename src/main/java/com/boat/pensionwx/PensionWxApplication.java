package com.boat.pensionwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.boat.pension.mapper"})
public class PensionWxApplication  {
    public static void main(String[] args) {
        SpringApplication.run(PensionWxApplication.class, args);
    }

}
