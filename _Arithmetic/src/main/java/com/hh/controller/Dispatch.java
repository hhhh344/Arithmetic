package com.hh.controller;


import ch.qos.logback.core.util.FileUtil;
import com.hh.dao.impl.CalculateUtilsImpl;
import com.hh.dao.impl.ExpressionDaoImpl;
import com.hh.dao.impl.FileUtilsImpl;
import com.hh.entity.Expression;
import com.hh.entity.Param;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.List;
import java.util.UUID;

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
        // fileUtils.writeExpressionInFile(ExpressionFile,expressionDao.expressions);
        // //将答案写入文件
        // fileUtils.writeAnswerInFile(AnswerFile, expressionDao.expressions);
        // //比较答案文件里的答案是否和表达式的结果一致并写入文件
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
        System.out.println(json.getJSONObject(0).get("expression"));
        File ExpressionFile = fileUtils.createNewFile("Exercises.txt");
        File AnswerFile = fileUtils.createNewFile("Answers.txt");
        //把题目写入文件
        fileUtils.writeExpressionInFile(ExpressionFile, json);
        //将答案写入文件
        fileUtils.writeAnswerInFile(AnswerFile, json);
        return json.toString();
    }

    @RequestMapping("/download")
    public @ResponseBody String downloadFile(@RequestParam String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            String realPath = "file/"+fileName;
            File file = new File(realPath);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Length",""+file.length());
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the file successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the file failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping("/upload")
    public @ResponseBody String Upload(@RequestParam String filename, @RequestParam MultipartFile file) throws IOException {
        File file1 = null;
        InputStream ins = file.getInputStream();
        file1 = fileUtils.createNewFile(filename);
        inputStreamToFile(ins, file1);
        ins.close();
        return "ok";
    }

    @RequestMapping("/verify")
    public @ResponseBody String Verify() throws IOException {
        File uploadExpressionsFile = new File("file/UploadExpressionsFile.txt");
        File uploadAnswersFile = new File("file/uploadAnswersFile.txt");
        if(!uploadAnswersFile.exists() || !uploadExpressionsFile.exists()) {
            return null;
        }
        JSONArray json = fileUtils.mapToJSON(uploadExpressionsFile, uploadAnswersFile);
        return json.toString();
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
