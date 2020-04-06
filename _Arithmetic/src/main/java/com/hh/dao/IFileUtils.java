package com.hh.dao;

import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;

import java.io.File;
import java.io.IOException;

/**
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
}
