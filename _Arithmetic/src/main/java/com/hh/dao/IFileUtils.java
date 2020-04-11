package com.hh.dao;

import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * 文件操作的接口
 * @author 戮漠
 */
public interface IFileUtils {

    /**
     * 创建新的txt文件
     * @param filename 文件名
     * @return File对象
     */
    public File createNewFile(String filename) throws IOException;

    /**
     * 将表达式写入文件
     * @param file txt文件
     * @param expressionList 表达式列表
     * @return 是否成功写入
     */
    public boolean writeExpressionInFile(File file, ExpressionList expressionList) throws IOException;

    /**
     * 将答案写入文件
     * @param file txt文件
     * @param expressionList 表达式列表
     * @return 是否成功写入
     */
    public boolean writeAnswerInFile(File file, ExpressionList expressionList) throws IOException;

    /**
     * 判断表达式和计算结果是否一致
     * @param expressionFile 表达式文件
     * @param answerFile 答案文件
     * @param gradeFile 统计文件
     * @return 答案是否全部正确
     */
    public boolean writeGradeInFile(File expressionFile, File answerFile, File gradeFile) throws IOException;

    /**
     * 获取表达式的题号和结果
     * @param expressionFile 表达式文件
     * @return 题号和结果的Map序列
     */
    public Map<Integer, String> getExpressionFileMap(File expressionFile) throws IOException;

    /**
     * 获取题号和答案
     * @param answerFile 答案文件
     * @return 题号和答案的Map序列
     */
    public Map<Integer, String> getAnswerFileMap(File answerFile) throws IOException;

    /**
     * 下载文件
     * @param filename 文件名
     * @param response  响应
     */
    public void download(String filename, HttpServletResponse response) throws IOException;
}
