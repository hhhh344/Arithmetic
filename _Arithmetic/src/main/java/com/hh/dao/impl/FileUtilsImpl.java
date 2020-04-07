package com.hh.dao.impl;

import com.hh.dao.IFileUtils;
import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 戮漠
 * 文件操作
 */
public class FileUtilsImpl implements IFileUtils {

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
            if (expressionList.getExpressionsList().size() != index-1) {
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
        return true;
    }

    @Override
    public boolean writeAnswerInFile(File file, ExpressionList expressionList) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        int index = 1;
        for (Expression expression : expressionList.getExpressionsList()) {
            bw.write(index++ + ". " + cal.resultToString(expression.getResult()));
            if (expressionList.getExpressionsList().size() != index-1) {
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
        return true;
    }

    @Override
    public boolean writeGradeInFile(File expressionFile, File answerFile, File gradeFile) throws IOException {
        FileWriter fw = new FileWriter(gradeFile);
        BufferedWriter bw = new BufferedWriter(fw);
        Map<Integer, String> expressionFileMap = getExpressionFileMap(expressionFile);
        Map<Integer, String> answerFileMap = getAnswerFileMap(answerFile);

        int correctCount = 0;
        int wrongCount = 0;

        String correctString = "Correct:(";
        String wrongString = "Wrong:(";

        for (Map.Entry<Integer, String> item : expressionFileMap.entrySet()) {
            Integer key = item.getKey();
            //比较两个答案字符串是否一致
            if(item.getValue().equals(answerFileMap.get(key))) {
                correctCount++;
                if(correctCount == 1) {
                    correctString += key;
                }
                else {
                    correctString += ", " + key;
                }
            }
            else {
                wrongCount++;
                if(wrongCount == 1) {
                    wrongString += key;
                }
                else {
                    wrongString += ", " + key;
                }
            }
        }
        correctString += ")";
        wrongString += ")";

        StringBuilder correctStringBuilder = new StringBuilder(correctString);
        StringBuilder wrongStringBuilder = new StringBuilder(wrongString);

        correctStringBuilder.insert(correctStringBuilder.indexOf("("), correctCount);
        wrongStringBuilder.insert(wrongStringBuilder.indexOf("("), wrongCount);

        bw.write(correctStringBuilder.toString());
        bw.newLine();
        bw.write(wrongStringBuilder.toString());

        bw.close();
        fw.close();

        if(wrongCount == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, String> getExpressionFileMap(File expressionFile) throws IOException {
        FileReader fr = new FileReader(expressionFile);
        BufferedReader br = new BufferedReader(fr);
        Map<Integer, String> expressionFileMap = new HashMap<>();
        String line = br.readLine();

        Integer number;
        Integer[] result;
        Expression expression;

        while(line != null && line != "\n") {
            number = Integer.parseInt(line.substring(0, line.indexOf(".")));
            expression = exp.stringToExpression(line.substring(line.indexOf(".")+1));
            result = cal.getExpressionResult(expression);
            expressionFileMap.put(number, cal.resultToString(result));
            line = br.readLine();
        }

        br.close();
        fr.close();
        return expressionFileMap;
    }

    @Override
    public Map<Integer, String> getAnswerFileMap(File answerFile) throws IOException {
        FileReader fr = new FileReader(answerFile);
        BufferedReader br = new BufferedReader(fr);
        Map<Integer, String> answerFileMap = new HashMap<>();
        String line = br.readLine();

        Integer number;
        String answerString[];

        while(line != null && line != "\n") {
            answerString = line.split(" ");
            number = Integer.parseInt(answerString[0].substring(0, answerString[0].indexOf(".")));
            if (answerString.length == 2) {
                answerFileMap.put(number, answerString[1]);
            }
            line = br.readLine();
        }

        br.close();
        fr.close();
        return answerFileMap;
    }
}
