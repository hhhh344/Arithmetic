package com.hh.controller;

import com.hh.dao.impl.ExpressionDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Dispatch {

    /**
     * @param expressionsCount 要生成题目的数量
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     * @return 生成的题目
     */
    @RequestMapping("/hh")
    static @ResponseBody
    String getParam(@RequestParam int expressionsCount, @RequestParam int range) {

        ExpressionDaoImpl temp = new ExpressionDaoImpl();
        return temp.generateExpression(expressionsCount, range);
    }
}
