package com.boat.pension;

import com.boat.pension.controller.AdminController;
import com.boat.pension.model.Admin;
import com.boat.pension.service.AdminService;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PensionAdminApplicationTests.class)
//@MapperScan("com.boat.pension.mapper")
class PensionAdminApplicationTests {
//    @Autowired
//    private AdminController adminController;
//
//    @Test
//    public void getAdminByAdminId(){
//        Integer id = 1;
//
//        System.out.println(adminController.getItem(id));
//
//    }

    @Test
    void contextLoads() {
        System.out.println("hello test");
    }

    @Test
    void test1() {
        System.out.println("hello test1");
    }
}
