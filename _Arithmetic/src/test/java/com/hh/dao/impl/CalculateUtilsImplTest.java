package com.hh.dao.impl;

import com.hh.entity.Expression;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CalculateUtilsImplTest {

    CalculateUtilsImpl cal = new CalculateUtilsImpl();
    ExpressionDaoImpl exp = new ExpressionDaoImpl();

    @Test
    void getPostfixExpression() {
        /**
         * 将表达式转化为后缀表达式
         */
        for(int i = 0; i < 100; i++) {
            Expression expression = exp.generateExpression(33);
            System.out.println(exp.expressionToString(expression));
            System.out.println(cal.getPostfixExpression(expression));
        }
    }

    @Test
    void getExpressionResult() {
        /**
         * 计算表达式结果
         */
        Expression temp;
        for (int i = 0; i < 10000; i++) {
            temp = exp.generateExpression(1000);
            System.out.println(exp.expressionToString(temp));
            System.out.println(cal.getPostfixExpression(temp));
            for (int item : cal.getExpressionResult(temp)){
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    @Test
    void addition() {
        /**
         * 加法
         */
        for (int i = 0; i < 20; i++) {
            Integer[] num1, num2;
            num1 = exp.generateFraction(10);
            num2 = exp.generateFraction(10);
//            num1[2] = 0;
            for(int item : num1){
                System.out.print(item + " ");
            }
            System.out.print("+ ");
            for(int item : num2){
                System.out.print(item + " ");
            }
            System.out.println("");
            for(int item : cal.addition(num1, num2)){
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }

    @Test
    void subtraction() {
        /**
         * 减法
         */
        for (int i = 0; i < 20; i++) {
            Integer[] num1, num2;
            num1 = exp.generateFraction(10);
            num2 = exp.generateFraction(10);
//            num1[2] = 0;
            for(int item : num1){
                System.out.print(item + " ");
            }
            System.out.print("- ");
            for(int item : num2){
                System.out.print(item + " ");
            }
            System.out.println("");
            for(int item : cal.subtraction(num1, num2)){
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }

    @Test
    void multiplication() {
        /**
         * 乘法
         */
        for (int i = 0; i < 20; i++) {
            Integer[] num1, num2;
            num1 = exp.generateFraction(10);
            num2 = exp.generateFraction(10);
//            num1[2] = 0;
            for(int item : num1){
                System.out.print(item + " ");
            }
            System.out.print("* ");
            for(int item : num2){
                System.out.print(item + " ");
            }
            System.out.println("");
            for(int item : cal.multiplication(num1, num2)){
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }

    @Test
    void division() {
        /**
         * 除法
         */
        for (int i = 0; i < 100; i++) {
            Integer[] num1, num2;
            num1 = exp.generateFraction(10);
            num2 = exp.generateFraction(10);
//            num2[1] = 0;
//            num2[2] = 0;
            for(int item : num1){
                System.out.print(item + " ");
            }
            System.out.print("÷ ");
            for(int item : num2){
                System.out.print(item + " ");
            }
            System.out.println("");
            for(int item : cal.division(num1, num2)){
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }
}