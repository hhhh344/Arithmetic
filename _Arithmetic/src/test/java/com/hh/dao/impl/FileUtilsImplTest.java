package com.hh.dao.impl;

import com.hh.entity.Expression;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsImplTest {

    FileUtilsImpl fui = new FileUtilsImpl();
    ExpressionDaoImpl exp = new ExpressionDaoImpl();
    CalculateUtilsImpl cal = new CalculateUtilsImpl();
    @Test
    void createNewFile() throws IOException {
        /**
         * 创建一个新文件
         */
        fui.createNewFile("test1.txt");
    }

    @Test
    void writeExpressionInFile() throws IOException, JSONException {
        /**
         * 将表达式写入文件
         */
        exp.generateMultiExpression(100, 10);
        File file1 = fui.createNewFile("test1.txt");
        File file2 = fui.createNewFile("test2.txt");
        JSONArray json = new JSONArray();
        int index = 1;
        for(Expression expression : exp.expressions.getExpressionsList()) {
            JSONObject jo = new JSONObject();
            jo.put("num", index++);
            jo.put("expression", exp.expressionToString(expression));
            jo.put("answer", cal.resultToString(expression.getResult()));
            json.put(jo);
        }
        System.out.println(fui.writeExpressionInFile(file1, json));
        System.out.println(fui.writeAnswerInFile(file2, json));
    }

    @Test
    void writeGradeInFile() throws IOException {
        /**
         * 将统计结果写入文件
         */
        File expressionFile = new File("file/test1.txt");
        File answerFile = new File("file/test2.txt");
        File gradeFile = new File("file/test3.txt");
        System.out.println(fui.writeGradeInFile(expressionFile, answerFile, gradeFile));
    }
}