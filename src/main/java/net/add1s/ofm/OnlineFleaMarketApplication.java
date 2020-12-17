package net.add1s.ofm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.add1s.ofm.mapper")
public class OnlineFleaMarketApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlineFleaMarketApplication.class, args);
    }
}
