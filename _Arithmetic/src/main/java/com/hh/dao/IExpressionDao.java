package com.hh.dao;

import com.hh.entity.Expression;

/**
 * @author 戮漠
 *对表达式的操作的接口
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
}
