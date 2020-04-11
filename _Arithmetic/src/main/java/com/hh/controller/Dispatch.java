package com.hh.controller;


import ch.qos.logback.core.util.FileUtil;
import com.hh.dao.impl.CalculateUtilsImpl;
import com.hh.dao.impl.ExpressionDaoImpl;
import com.hh.dao.impl.FileUtilsImpl;
import com.hh.entity.Expression;
import com.hh.entity.Param;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.Buffer;
import java.util.List;

/**
 * @author HH
 */
@Controller
public class Dispatch {
    static ExpressionDaoImpl expressionDao = new ExpressionDaoImpl();
    static CalculateUtilsImpl calculateUtils = new CalculateUtilsImpl();
    static FileUtilsImpl fileUtils = new FileUtilsImpl();
    static Param param = new Param();
    /**
     * @param number 要生成题目的数量
     * @param range 题目中数值（自然数、真分数和真分数分母）的范围
     * @return 生成的题目
     */
    @RequestMapping("/hh")
    static @ResponseBody
    String getParam(@RequestParam int number, @RequestParam int range) throws IOException {

        System.out.println(""+number+range);


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

    @RequestMapping("/generateExpression")
    static @ResponseBody
    String generateExpression(@RequestParam int number, @RequestParam int range) throws IOException {
        param.setNumber(number);
        param.setRange(range);
        //如果无法生成足够题目
        if(!expressionDao.generateMultiExpression(param.getNumber(), param.getRange())) {
            return "0";
        }
        List<Expression> expressions = expressionDao.expressions.getExpressionsList();

        JSONArray json = new JSONArray();
        int index = 1;
        for(Expression exp : expressions) {
            JSONObject jo = new JSONObject();
            jo.put("num", index++);
            jo.put("expression", expressionDao.expressionToString(exp));
            jo.put("answer", calculateUtils.resultToString(exp.getResult()));
            json.put(jo);
        }

        File ExpressionFile = fileUtils.createNewFile("Exercises.txt");
        File AnswerFile = fileUtils.createNewFile("Answers.txt");
        //把题目写入文件
        fileUtils.writeExpressionInFile(ExpressionFile,expressionDao.expressions);
        //将答案写入文件
        fileUtils.writeAnswerInFile(AnswerFile, expressionDao.expressions);
        return json.toString();
    }

    @RequestMapping("/downloadFile")
    static @ResponseBody void downloadFile(@RequestParam String filename) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        fileUtils.download(filename, response);
    }
}
