package com.hh.dao.impl;

import com.hh.dao.IFileUtils;
import com.hh.entity.Expression;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件操作
 * @author 戮漠
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
    public boolean writeExpressionInFile(File file, JSONArray expressionList) throws IOException, JSONException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int index = 0; index < expressionList.length(); index++) {
            bw.write(index+1 + ". " + expressionList.getJSONObject(index).get("expression"));
            if (expressionList.length() != index + 1) {
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
        return true;
    }

    @Override
    public boolean writeAnswerInFile(File file, JSONArray expressionList) throws IOException, JSONException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int index = 0; index < expressionList.length(); index++) {
            bw.write(index+1 + ". " + expressionList.getJSONObject(index).get("answer"));
            if (expressionList.length() != index + 1) {
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
        Map<Integer, String> expressionFileMap = getExpressionFileAnswerMap(expressionFile);
        Map<Integer, String> answerFileMap = getAnswerFileMap(answerFile);

        int correctCount = 0;
        int wrongCount = 0;

        StringBuilder correctString = new StringBuilder("Correct:(");
        StringBuilder wrongString = new StringBuilder("Wrong:(");

        for (Map.Entry<Integer, String> item : expressionFileMap.entrySet()) {
            Integer key = item.getKey();
            //比较两个答案字符串是否一致
            if(item.getValue().equals(answerFileMap.get(key))) {
                correctCount++;
                if(correctCount == 1) {
                    correctString.append(key);
                }
                else {
                    correctString.append("," + key);
                }
            }
            else {
                wrongCount++;
                if(wrongCount == 1) {
                    wrongString.append(key);
                }
                else {
                    wrongString.append("," + key);
                }
            }
        }
        correctString.append(")");
        wrongString.append(")");
        correctString.insert(correctString.indexOf("("),correctCount);
        wrongString.insert(wrongString.indexOf("("),wrongCount);


        bw.write(correctString.toString());
        bw.newLine();
        bw.write(wrongString.toString());

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
        String expression;

        while(line != null && line != "\n") {
            number = Integer.parseInt(line.substring(0, line.indexOf(".")));
            expression = line.substring(line.indexOf(".")+1);
            expressionFileMap.put(number, expression);
            line = br.readLine();
        }

        br.close();
        fr.close();
        return expressionFileMap;
    }

    @Override
    public Map<Integer, String> getExpressionFileAnswerMap(File expressionFile) throws IOException {
        FileReader fr = new FileReader(expressionFile);
        BufferedReader br = new BufferedReader(fr);
        Map<Integer, String> expressionFileAnswerMap = new HashMap<>();
        String line = br.readLine();

        Integer number;
        Integer[] result;
        Expression expression;

        while(line != null && line != "\n") {
            number = Integer.parseInt(line.substring(0, line.indexOf(".")));
            expression = exp.stringToExpression(line.substring(line.indexOf(".")+1));
            result = cal.getExpressionResult(expression);
            expressionFileAnswerMap.put(number, cal.resultToString(result));
            line = br.readLine();
        }

        br.close();
        fr.close();
        return expressionFileAnswerMap;
    }

    @Override
    public Map<Integer, String> getAnswerFileMap(File answerFile) throws IOException {
        FileReader fr = new FileReader(answerFile);
        BufferedReader br = new BufferedReader(fr);
        Map<Integer, String> answerFileMap = new HashMap<>();
        String line = br.readLine();

        Integer number;
        String[] answerString;

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

    @Override
    public JSONArray mapToJSON(File expressionFile, File answerFile) throws IOException, JSONException {
        Map<Integer, String> expressionMap = getExpressionFileMap(expressionFile);
        Map<Integer, String> resultMap = getExpressionFileAnswerMap(expressionFile);
        //如果出现未知字符
        if (resultMap == null){
            return null;
        }
        Map<Integer, String> answerMap = getAnswerFileMap(answerFile);
        JSONArray json = new JSONArray();
        for(Map.Entry<Integer, String> item : expressionMap.entrySet()) {
            Integer key = item.getKey();
            String result = resultMap.get(key);
            String answer = answerMap.get(key);
            JSONObject jo = new JSONObject();
            jo.put("num", key);
            jo.put("expression", item.getValue());
            jo.put("result", result);
            jo.put("answer", answer);
            if(result.equals(answer)) {
                jo.put("correctness", "正确");
            }
            else {
                jo.put("correctness", "错误");
            }
            json.put(jo);
        }
        File gradeFile = createNewFile("Grade.txt");
        writeGradeInFile(expressionFile, answerFile, gradeFile);
        return json;
    }
}
