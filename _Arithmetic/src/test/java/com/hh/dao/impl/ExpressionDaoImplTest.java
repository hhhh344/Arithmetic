package com.hh.dao.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionDaoImplTest {

    @Test
    void generateNaturalNum() {
        ExpressionDaoImpl e = new ExpressionDaoImpl();
        for (int i =0;i<10;i++){
            System.out.println(e.generateNaturalNum(11)[1]);
        }
    }
}