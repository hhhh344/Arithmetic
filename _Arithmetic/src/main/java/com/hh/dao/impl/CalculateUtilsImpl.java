package com.hh.dao.impl;

import com.hh.dao.ICalculateUtils;
import com.hh.entity.Expression;

import java.util.List;
import java.util.Stack;

/**
 * @author 戮漠
 *
 * 计算
 */

public class CalculateUtilsImpl implements ICalculateUtils {

    /**
     * 获取操作符的优先级
     * @param op 操作符
     * @return 操作符的优先级
     */
    private int getPriorityValue(String op) {
        switch (op) {
            case "#":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                throw new RuntimeException("没有该类型的操作符!");
        }
    }

    /**
     *
     * @param op1 操作符1
     * @param op2 操作符2
     * @return op1优先级高则返回1,否则返回0
     */
    private boolean comparePriority(String op1, String op2) {
        return getPriorityValue(op1) > getPriorityValue(op2);
    }



    @Override
    public String getPostfixExpression(Expression expression) {
        Stack<String> S1 = new Stack<>();
        Stack<String> S2 = new Stack<>();

        List<Integer[]> parameterList = expression.getParameterList();
        Integer[] num;

        List<String> operatorList = expression.getOperatorList();
        String operator;

        int parameterIndex = 0;
        int operatorIndex = 0;

        //获取表达式的模式
        String pattern = expression.getPattern();
        S1.push("#");

        for(int i = 0; i < pattern.length(); i++) {
            char temp = pattern.charAt(i);
            switch (temp) {
                //若取出的字符是操作数，则分析出完整的运算数，该操作数直接送入S2栈
                case 'n':
                    num = parameterList.get(parameterIndex++);
                    if(num[0] == 0) {
                        S2.push(num[1].toString());
                    }
                    else {
                        S1.push("(");
                        S2.push(num[1].toString());
                        S1.push("+");
                        S2.push(num[2].toString());
                        S1.push("/");
                        S2.push(num[3].toString());
                        while(S1.peek() != "(") {
                            S2.push(S1.pop());
                        }
                        S1.pop();
                    }
                    break;
                case '#':
                    operator = operatorList.get(operatorIndex);
                    //1.如果S1为空，或栈顶为"("，则将该运算符进S1栈
                    if(S1.peek() == "#" || S1.peek() == "(") {
                        S1.push(operator);
                        operatorIndex++;
                    }
                    //2.如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈
                    else if(comparePriority(operator, S1.peek())) {
                        S1.push(operator);
                        operatorIndex++;
                    }
                    //3.否则，将S1栈的栈顶运算符弹出，送入S2栈中，跳回1
                    else {
                        S2.push(S1.pop());
                    }
                    break;
                //若取出的字符是“（”，则直接送入S1栈顶。
                case '(':
                    S1.push("(");
                    break;
                //若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
                case ')':
                    while(S1.peek() != "(") {
                        S2.push(S1.pop());
                    }
                    if(S1.peek() == "(") {
                        S1.pop();
                    }
                default:
            }
        }
        while(S1.peek() != "#") {
            S2.push(S1.pop());
        }
        return S2.toString();
    }

    @Override
    public String getExpressionResult(String postfixExpression) {
        return null;
    }
}
