package com.hh.dao.impl;

import com.hh.dao.IExpressionDao;
import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;
import com.hh.entity.Pattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 对表达式进行操作
 * @author 戮漠
 */
public class ExpressionDaoImpl implements IExpressionDao {

    public ExpressionList expressions = new ExpressionList();
    private Random rand = new Random();

    public ExpressionDaoImpl() {
        super();
    }

    @Override
    public Expression generateExpression(int range) {
        Expression exp = new Expression();
        List<Integer[]> parameterList = new ArrayList<>();
        List<String> operatorList = new ArrayList<>();
        String pattern = generatePattern();

        for(int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case 'n':
                    if(rand.nextFloat() > 0.5) {
                        parameterList.add(generateNaturalNum(range));
                    }
                    else {
                        parameterList.add(generateFraction(range));
                    }
                    break;

                case '#':
                    operatorList.add(generateOperator());
                    break;

                default: ;
            }
        }

        exp.setPattern(pattern);
        exp.setParameterList(parameterList);
        exp.setOperatorList(operatorList);

        return exp;
    }

    @Override
    public Integer[] generateNaturalNum(int range) {

        Integer[] naturalNum = new Integer[2];
        naturalNum[0] = 0;
        naturalNum[1] = rand.nextInt(range);
        return naturalNum;
    }

    @Override
    public Integer[] generateFraction(int range) {
        Integer[] fraction = new Integer[4];
        fraction[0] = 1;
        fraction[1] = rand.nextInt(range);
        fraction[3] = rand.nextInt(range-1)+2;
        //分子必须比分母小
        fraction[2] = rand.nextInt(fraction[3]-1)+1;
        //调用方法化简为真分数
        return getProperFraction(fraction);
    }

    @Override
    public Integer[] getProperFraction(Integer[] fraction) {
        int m, n, rem;
        fraction[1] += fraction[2]/fraction[3];
        fraction[2] %= fraction[3];
        m = fraction[3];
        n = fraction[2];
        while(n > 0){
            rem = m % n;
            m = n;
            n = rem;
        }
        fraction[2] /=m;
        fraction[3] /=m;
        return fraction;
    }

    @Override
    public String generateOperator() {
        String[] ope = new String[]{"+","-","*","/"};
        return ope[rand.nextInt(4)];
    }

    @Override
    public String generatePattern() {
        int temp = rand.nextInt(14);
        return Pattern.PATTERN_MAP.get(temp);
    }

    @Override
    public String expressionToString(Expression exp) {
        String pattern = exp.getPattern();
        String returnString = "";

        List<Integer[]> parameterList = exp.getParameterList();
        Integer[] num;

        List<String> operatorList = exp.getOperatorList();
        String operator;

        int parameterIndex = 0;
        int operatorIndex = 0;

        for(int i = 0; i < pattern.length(); i++) {
            char temp = pattern.charAt(i);
            switch (temp) {
                case '(':
                case ')':
                    returnString += temp + " ";
                    break;

                case 'n':
                    num = parameterList.get(parameterIndex++);
                    if(num[0] == 0) {
                        returnString += num[1] + " ";
                    }
                    else {
                        //如果带分数前面的整数为0，则只打印分数部分
                        if(num[1] != 0) {
                            returnString += num[1] + "'" + num[2] + "/" + num[3] + " ";
                        }
                        else {
                            returnString += num[2] + "/" + num[3] + " ";
                        }
                    }
                    break;

                case '#':
                    operator = operatorList.get(operatorIndex++);
                    if(operator.contains("/")) {
                        returnString += "÷ ";
                    }
                    else {
                        returnString += operator + " ";
                    }
                    break;

                default: ;
            }
        }
        returnString += "=";
        return returnString;
    }

    @Override
    public boolean isQualified(Integer[] result) {
        if(result[0] == 4) {
            return false;
        }
        return true;
    }

    @Override
    public boolean generateMultiExpression(int number, int range) {
        Expression exp;
        Integer[] result;
        List<Expression> expressionsList = new ArrayList<>();
        CalculateUtilsImpl cal = new CalculateUtilsImpl();
        int i, j;
        for (i = 0, j = 0; i < number && j < 100*number; j++) {
            exp = generateExpression(range);
            result = cal.getExpressionResult(exp);
            if(isQualified(result)) {
                exp.setResult(result);
                expressionsList.add(exp);
                i++;
            }
        }
        if(expressionsList.size() == number) {
            expressions.setExpressionsList(expressionsList);
            return true;
        }
        throw new RuntimeException("没有生成足够的表达式！！");
    }

    @Override
    public Expression stringToExpression(String expressionString) {
        Expression exp = new Expression();
        List<Integer[]> parameterList = new ArrayList<>();
        List<String> operateList = new ArrayList<>();
        String pattern = "";
        String[] str = expressionString.split("\\s");

        for (String item : str) {
            if(item.matches("^[()]$")) {
                pattern += item;
            }
            else if(item.matches("^[\\+\\-\\*÷]$")) {
                if(item.equals("÷")) {
                    operateList.add("/");
                }
                else {
                    operateList.add(item);
                }
                pattern += "#";
            }
            //整数
            else if(item.matches("^[0-9]+$")) {
                pattern += "n";
                Integer[] num = new Integer[2];
                num[0] = 0;
                num[1] = Integer.parseInt(item);
                parameterList.add(num);
            }
            //分数
            else if(item.matches("^([0-9]+')?[0-9]+\\/[0-9]+$")) {
                pattern += "n";
                Integer[] num = new Integer[4];
                num[0] = 1;
                if(item.contains("'")) {
                    num[1] = Integer.parseInt(item.substring(0, item.indexOf("'")));
                    num[2] = Integer.parseInt(item.substring(item.indexOf("'")+1, item.indexOf("/")));
                }
                else {
                    num[1] = 0;
                    num[2] = Integer.parseInt(item.substring(0, item.indexOf("/")));
                }
                num[3] = Integer.parseInt(item.substring(item.indexOf("/")+1));
                parameterList.add(num);
            }
            else if(item.matches("^=$")) {

            }
            else {
                throw new RuntimeException("将字符串表达式转化为Expression时出现了未知字符");
            }
        }
        exp.setOperatorList(operateList);
        exp.setParameterList(parameterList);
        exp.setPattern(pattern);
        return exp;
    }
}
