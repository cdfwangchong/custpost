package com.ctg.custpost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.ctg.custpost.dao")
@SpringBootApplication
public class CustpostApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustpostApplication.class, args);
    }

}
