package com.hh.dao.impl;

import com.hh.entity.Expression;
import com.hh.entity.ExpressionList;
import org.junit.jupiter.api.Test;


class ExpressionDaoImplTest {
    private ExpressionDaoImpl Expre = new ExpressionDaoImpl();
    private CalculateUtilsImpl Cal = new CalculateUtilsImpl();

    @Test
    void generateNaturalNum() {
        for (int i =0;i<10;i++){
            System.out.println(Expre.generateNaturalNum(11)[1]);
        }
    }

    @Test
    void generateOperator() {
        for(int i=0;i<10;i++){
            System.out.println(Expre.generateOperator());
        }
    }

    @Test
    void generatePattern() {
        for (int i=0;i<10;i++){
            System.out.println(Expre.generatePattern());
        }
    }

    @Test
    void generateFraction() {
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
        for(int i = 0; i < 10000; i++) {
            System.out.println(Expre.expressionToString(Expre.generateExpression(10)));
        }
    }

    @Test
    void generateMultiExpression() {
        Expre.generateMultiExpression(1, 10);
        for (Expression item : Expre.expressions.getExpressionsList()){
            System.out.println(Expre.expressionToString(item) + " " + Cal.resultToString(item.getResult()));
        }
    }

    @Test
    void stringToExpression() {
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