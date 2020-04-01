package com.hh.dao;

/**
 * @author 戮漠
 * 计算操作的接口
 */
public interface ICalculateUtils {

    /**
     *
     */



    /**
     * @param postfixExpression 后缀表达式
     * @return 表达式计算结果
     */
    public String getExpressionResult(String postfixExpression);



    /**
     *
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    public String getPostfixExpression(String expression);



    /**
     * 如果结果是分数，调用此方法
     * @param expressionResult 表达式计算结果(分数)
     * @return 化简后的计算结果(真分数)
     */
    public String simplifyExpressionResult(String expressionResult);

}
