package com.hh.dao.impl;

import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;
import org.junit.jupiter.api.Test;


class ExpressionDaoImplTest {
    private ExpressionDaoImpl Expre = new ExpressionDaoImpl();
    private CalculateUtilsImpl Cal = new CalculateUtilsImpl();

    @Test
    void generateNaturalNum() {
        /**
         * 生成自然数
         */
        for (int i =0;i<10;i++){
            System.out.println(Expre.generateNaturalNum(11)[1]);
        }
    }

    @Test
    void generateOperator() {
        /**
         * 生成操作符
         */
        for(int i=0;i<10;i++){
            System.out.println(Expre.generateOperator());
        }
    }

    @Test
    void generatePattern() {
        /**
         * 生成模式
         */
        for (int i=0;i<10;i++){
            System.out.println(Expre.generatePattern());
        }
    }

    @Test
    void generateFraction() {
        /**
         * 生成分数
         */
        Integer[] fra;
        for(int i=0;i<100;i++){
            fra = Expre.generateFraction(100);
            for(int j : fra){
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    void getProperFraction() {
        /**
         * 化简分数
         */
        Integer[] fra;
        for(int i=0;i<100;i++){
            fra = Expre.generateFraction(100);
            for(int j : fra){
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println("");
            Expre.getProperFraction(fra);
            for (int k : fra){
                System.out.print(k);
                System.out.print(" ");
            }
            System.out.println("\n");
        }
    }

    @Test
    void generateExpression() {
        /**
         * 生成一个完整的表达式
         */
        for(int i = 0; i < 10000; i++) {
            System.out.println(Expre.expressionToString(Expre.generateExpression(10)));
        }
    }

    @Test
    void generateMultiExpression() {
        /**
         * 一次生成多道表达式
         */
        Expre.generateMultiExpression(100, 10);
        for (Expression item : Expre.expressions.getExpressionsList()){
            System.out.println(Expre.expressionToString(item) + " " + Cal.resultToString(item.getResult()));
        }
    }

    @Test
    void stringToExpression() {
        /**
         * 将字符串表达式化为自定义表达式
         */
        Expression expression;
        String str;

        for (int i = 0; i < 100; i++) {
            expression = Expre.generateExpression(2);
            str = Expre.expressionToString(expression);
            System.out.println("转化前:" + str);
            expression = Expre.stringToExpression(str);
            str = Expre.expressionToString(expression);
            System.out.println("转化后:" + str);
            System.out.println(Cal.getPostfixExpression(expression));
        }
    }
}