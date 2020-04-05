package com.hh.dao.impl;

import com.hh.entity.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateUtilsImplTest {

    CalculateUtilsImpl cal = new CalculateUtilsImpl();
    ExpressionDaoImpl exp = new ExpressionDaoImpl();

    @Test
    void getPostfixExpression() {
//        for(int i = 0; i < 10000; i++) {
            Expression expression = exp.generateExpression(33);
            System.out.println(exp.expressionToString(expression));
            System.out.println(cal.getPostfixExpression(expression));
            for (String item : cal.getPostfixExpression(expression)){
                System.out.println(item);
            }
//        }
    }
}