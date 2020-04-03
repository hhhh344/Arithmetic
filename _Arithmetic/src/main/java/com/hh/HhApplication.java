package com.hh;

import com.hh.entity.Pattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class HhApplication {

    public static void main(String[] args) {
        System.out.println(Pattern.patternMap.get(0));
        SpringApplication.run(HhApplication.class, args);
    }

}
