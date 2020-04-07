package com.hh.controller;


import com.hh.dao.impl.CalculateUtilsImpl;
import com.hh.dao.impl.ExpressionDaoImpl;
import com.hh.dao.impl.FileUtilsImpl;
import com.hh.entity.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

/**
 * @author HH
 */
@Controller
public class Dispatch {

    /**
     * @param number 要生成题目的数量
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     * @return 生成的题目
     */
    @RequestMapping("/hh")
    static @ResponseBody
    String getParam(@RequestParam int number, @RequestParam int range) throws IOException {

        ExpressionDaoImpl expressionDao = new ExpressionDaoImpl();
        CalculateUtilsImpl calculateUtils = new CalculateUtilsImpl();
        FileUtilsImpl fileUtils = new FileUtilsImpl();
        File ExpressionFile = fileUtils.createNewFile("Exercises.txt");
        File AnswerFile = fileUtils.createNewFile("Answers.txt");
        File GradeFile = fileUtils.createNewFile("Grade.txt");

        //存储生成题目的参数
        Param param = new Param(number, range);
        //批量生成题目
        expressionDao.generateMultiExpression(param.getNumber(), param.getRange());
        //把题目写入文件
        fileUtils.writeExpressionInFile(ExpressionFile,expressionDao.expressions);
        //将答案写入文件
        fileUtils.writeAnswerInFile(AnswerFile, expressionDao.expressions);
        //比较答案文件里的答案是否和表达式的结果一致并写入文件
        fileUtils.writeGradeInFile(ExpressionFile, AnswerFile, GradeFile);

        return "ex="+param.getNumber()+"ran="+param.getRange();
    }
}
