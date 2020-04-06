package com.hh.dao.impl;

import com.hh.dao.IFileUtils;
import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;

import java.io.*;

/**
 * @author 戮漠
 * 文件操作
 */
public class FileUtilsImpl implements IFileUtils {

    /*ExpressionDaoImpl exp = new ExpressionDaoImpl();
    public void test () throws IOException {
        File file = new File("file/test2.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        else {
            file.delete();
            file.createNewFile();
        }

        exp.generateMultiExpression(10, 10);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for(Expression expression : exp.expressions.getExpressionsList()) {
            bw.write(exp.expressionToString(expression)+ "\n");
        }

        bw.close();
    }*/

    ExpressionDaoImpl exp = new ExpressionDaoImpl();
    CalculateUtilsImpl cal = new CalculateUtilsImpl();

    @Override
    public File createNewFile(String filename) throws IOException {
        File file = new File("file/" + filename);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    @Override
    public boolean writeExpressionInFile(File file, ExpressionList expressionList) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        int index = 1;
        for (Expression expression : expressionList.getExpressionsList()) {
            bw.write(index++ + ". " + exp.expressionToString(expression));
            bw.newLine();
        }
        bw.close();
        return true;
    }

    @Override
    public boolean writeAnswerInFile(File file, ExpressionList expressionList) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        int index = 1;
        for (Expression expression : expressionList.getExpressionsList()) {
            bw.write(index++ + ". " + cal.resultToString(expression.getResult()));
            bw.newLine();
        }
        bw.close();
        return true;
    }

    @Override
    public boolean writeGradeInFile(File expressionFile, File answerFile) throws IOException {
        if(!expressionFile.exists()){
            throw new FileNotFoundException();
        }

        ExpressionList expressionList = new ExpressionList();
        FileReader fr = new FileReader(expressionFile);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line!= null){

        }
        return false;
    }
}
