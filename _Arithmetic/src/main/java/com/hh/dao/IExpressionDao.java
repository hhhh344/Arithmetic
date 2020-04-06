package com.hh.dao;

import com.hh.entity.Expression;

import java.util.List;

/**
 * 表达式操作的接口
 * @author 戮漠
 */
public interface IExpressionDao {
    /**
     * 生成表达式
     * @param range
     * @return 自己定义的表达式
     */
    public Expression generateExpression(int range);


    /**
     * 生成一个自然数
     * @param range 取值范围
     * @return 自然数数组
     */
    public Integer[] generateNaturalNum(int range);

    /**
     * 生成真分数
     * @param range 取值范围
     * @return 真分数数组
     */
    public Integer[] generateFraction(int range);

    /**
     * 生成操作符
     * @return 操作符
     */
    public String generateOperator();

    /**
     * 生成表达式模型
     * @return 表达式模型
     */
    public String generatePattern();

    /**
     * 约分真分数
     * @param fraction 分数
     * @return 真分数
     */
    public Integer[] getProperFraction(Integer[] fraction);

    /**
     * 将表达式化为标准形式
     * @param exp 表达式
     * @return 标准形式字符串
     */
    public String expressionToString(Expression exp);

    /**
     * 判断表达式是否合格
     * @param result 计算结果
     * @return 是否成功
     */
    public boolean isQualified (Integer[] result);

    /**
     * 生成多个表达式
     * @param number
     * @param range
     * @return 存有多个表达式的列表
     */
    public boolean generateMultiExpression(int number, int range);

    /**
     * 将字符串表达式转化为规范表达式
     * @param str 字符串表达式
     * @return 规范表达式
     */
    public Expression stringToExpression(String str);
}
