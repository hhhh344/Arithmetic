package com.hh.dao.impl;

import com.hh.entity.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateUtilsImplTest {

    CalculateUtilsImpl cal = new CalculateUtilsImpl();
    ExpressionDaoImpl exp = new ExpressionDaoImpl();

    @Test
    void getPostfixExpression() {
        Expression expression = exp.generateExpression(10);
        System.out.println(exp.expressionToString(expression));
        System.out.println(cal.getPostfixExpression(expression));
    }
}