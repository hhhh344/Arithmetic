package com.hh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class dispatch {

    @RequestMapping("/test")
    static @ResponseBody
    String aaa() {
        return "bb";
    }
}
