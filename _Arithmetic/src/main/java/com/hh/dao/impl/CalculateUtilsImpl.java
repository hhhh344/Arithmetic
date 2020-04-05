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

    @Override
    public Integer[] getExpressionResult(Expression expression) {
        Stack<String> postfixExpression = getPostfixExpression(expression);
        ExpressionDaoImpl exp = new ExpressionDaoImpl();
        Stack<Integer[]> S3 = new Stack<>();
        Integer[] num1, num2, temp;

        for (String item : postfixExpression){
//            如果取出的元素是数字
            if(item.matches("[0-9]+")){
                S3.push(toInteger(item));
            }
//            如果取出的元素是操作符
            else if (item.matches("[\\+\\-\\*\\/]")){
//                栈顶元素应该在操作符后面
                num2 = S3.pop();
                num1 = S3.pop();
                temp = calculateTwoNumber(num1,num2,item);
//                如果两个数字不符合计算规则，除法出现被除数为零，减法出现负数
                if(temp[0]==4){
                    return temp;
                }
                S3.push(temp);
            }
        }
        if (S3.size()!=1){
            throw new RuntimeException("栈内元素剩余多于1！");
        }
        //将最终结果换为真分数
        return exp.getProperFraction(S3.peek());
    }


    @Override
    public Stack<String> getPostfixExpression(Expression expression) {
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
                        i--;
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
        return S2;
    }


    @Override
    public int getPriorityValue(String op) {
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

    @Override
    public boolean comparePriority(String op1, String op2) {
        return getPriorityValue(op1) > getPriorityValue(op2);
    }


    @Override
    public Integer[] calculateTwoNumber(Integer[] num1, Integer[] num2, String op) {
        switch (op){
            case "+":
                return addition(num1, num2);
            case "-":
                return subtraction(num1, num2);
            case "*":
                return multiplication(num1, num2);
            case "/":
                return division(num1, num2);
            default:
                throw new RuntimeException("没有该类型的运算符！");
        }
    }


    @Override
    public Integer[] addition(Integer[] num1, Integer[] num2) {
//        a1+a2; b1c2+b2c1; c1c1;
        num1[1] += num2[1];
        num1[2] = num1[2]*num2[3] +num2[2]*num1[3];
        num1[3] *=num2[3];
        return num1;
    }

    @Override
    public Integer[] subtraction(Integer[] num1, Integer[] num2) {
//        0; (a1c1+b1)c2-(a2c2+b2)c1; c1c2;
        int temp = (num1[1]*num1[3]+num1[2])*num2[3] - (num2[1]*num2[3]+num2[2])*num1[3];
//        如果计算过程出现负数
        if (temp < 0){
            num1[0] = 4;
            return num1;
        }
        num1[3] *=num2[3];
        num1[2] = temp;
        num1[1] = 0;
        return num1;
    }

    @Override
    public Integer[] multiplication(Integer[] num1, Integer[] num2) {
//        0; (a1c1+b1)c2+(a2c2+b2)c1; c1c2
//        a1*a2; a1b2c1+a2b1c2+b1b2; c1c2
        num1[2] = num1[1]*num2[2]*num1[3] + num2[1]*num1[2]*num2[3] + num1[2]*num2[2];
        num1[1] *= num2[1];
        num1[3] *= num2[3];
        return num1;
    }

    @Override
    public Integer[] division(Integer[] num1, Integer[] num2) {
//        除数为零，将数组的第一个元素标记为4
        if (num2[1]==0 && num2[2]==0){
            num1[0] = 4;
        }
        else {
//            0;（a1c1+b1)*c2; (a2c2+b2)*c1;
            int temp = (num1[1]*num1[3]+num1[2])*num2[3];
            num1[3] = (num2[1]*num2[3]+num2[2])*num1[3];
            num1[2] = temp;
            num1[1] = 0;
        }
        return num1;
    }

    @Override
    public Integer[] toInteger(String num) {
        Integer[] a1 = new Integer[4];
        a1[0] = 1;
        a1[1] = Integer.parseInt(num);
        a1[2] = 0;
        a1[3] = 1;
        return a1;
    }

    @Override
    public String resultToString(Integer[] result) {
        if(result[2] == 0){
            return result[1].toString();
        }
        else if (result[1] == 0){
            return result[2] + "/" + result[3];
        }
        else {
            return result[1] + "'" + result[2] + "/" + result[3];
        }
    }
}
