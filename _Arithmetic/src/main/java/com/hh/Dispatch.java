package com.hh;

import dao.impl.ExpressionDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Dispatch {

    @RequestMapping("/hh")
    static @ResponseBody
    String getParam(@RequestParam int n, @RequestParam int r) {
        ExpressionDaoImpl temp = new ExpressionDaoImpl();

        return temp.generateExpression(n, r);
    }
}
