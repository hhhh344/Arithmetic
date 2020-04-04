package com.hh.dao;

import com.hh.entity.Expression;

/**
 * @author 戮漠
 * 计算操作的接口
 */
public interface ICalculateUtils {

    /**
     * 将中缀表达式转化为后缀表达式
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    public String getPostfixExpression(Expression expression);

    /**
     * 计算表达式的结果
     * @param postfixExpression 后缀表达式
     * @return 表达式计算结果
     */
    public String getExpressionResult(String postfixExpression);


}
