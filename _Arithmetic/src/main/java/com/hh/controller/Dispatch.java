package com.hh.controller;


import com.hh.entity.Param;
import com.hh.entity.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Dispatch {

    /**
     * @param number 要生成题目的数量
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     * @return 生成的题目
     */
    @RequestMapping("/hh")
    static @ResponseBody
    String getParam(@RequestParam int number, @RequestParam int range) {

//        ExpressionDaoImpl temp = new ExpressionDaoImpl();
//        return temp.generateExpression(expressionsCount, range);
        System.out.println(Pattern.patternMap.get(0));
        Param en = new Param();
        en.setNumber(number);
        en.setRange(range);
        System.out.println("ex="+en.getNumber()+"ran="+en.getRange());
        return "ex="+en.getNumber()+"ran="+en.getRange();
    }
}
