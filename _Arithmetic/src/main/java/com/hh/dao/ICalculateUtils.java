package com.hh.dao;

import com.hh.entity.Expression;

import java.util.Stack;

/**
 * @author 戮漠
 * 计算操作的接口
 */
public interface ICalculateUtils {

    /**
     * 获取操作符的优先级
     * @param op 操作符
     * @return 操作符的优先级
     */
    public int getPriorityValue(String op);

    /**
     *  比较两个操作符的优先级
     * @param op1 操作符1
     * @param op2 操作符2
     * @return op1优先级高则返回1,否则返回0
     */
    public boolean comparePriority(String op1, String op2);

    /**
     * 将中缀表达式转化为后缀表达式
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    public Stack<String> getPostfixExpression(Expression expression);

    /**
     * 计算表达式的结果
     * @param expression 表达式
     * @return 表达式计算结果
     */
    public Integer[]  getExpressionResult(Expression expression);

    /**
     * 计算两个数的结果
     * @param num1 运算数1
     * @param num2 运算数2
     * @param op 运算符
     * @return 结果
     */
    public Integer[] calculateTwoNumber(Integer[] num1, Integer[] num2, String op);

    /**
     * 加法运算
     * @param num1
     * @param num2
     * @return
     */
    public Integer[] addition(Integer[] num1, Integer[] num2);

    /**
     * 减法运算
     * @param num1
     * @param num2
     * @return
     */
    public Integer[] subtraction(Integer[] num1, Integer[] num2);

    /**
     * 乘法运算
     * @param num1
     * @param num2
     * @return
     */
    public Integer[] multiplication(Integer[] num1, Integer[] num2);

    /**
     * 除法运算
     * @param num1
     * @param num2
     * @return
     */
    public Integer[] division(Integer[] num1, Integer[] num2);

    /**
     * 将Stack<String>里面的数字转换为Integer[]类型
     * @param num 整数
     * @return  自定义的类型
     */
    public Integer[] toInteger(String num);

    /**
     * 将计算结果打印出来
     * @param result 表达式结果
     * @return 字符串
     */
    public String resultToString(Integer[] result);

}
