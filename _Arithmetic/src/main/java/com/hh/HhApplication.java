package com.hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class HhApplication {

    @RequestMapping("/d")
    public static @ResponseBody
    String xx() {
        return "hihihi";
    }

    public static void main(String[] args) {
        SpringApplication.run(HhApplication.class, args);
    }

}
