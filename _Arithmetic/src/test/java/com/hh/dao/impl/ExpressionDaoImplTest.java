package com.hh.dao.impl;

import org.junit.jupiter.api.Test;


class ExpressionDaoImplTest {
    ExpressionDaoImpl Expre = new ExpressionDaoImpl();

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
        Integer[] fra = new Integer[4];
        for(int i=0;i<10;i++){
            fra = Expre.generateFraction(10);
            for(int j : fra){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    @Test
    void getProperFraction() {
        Integer[] fra = new Integer[4];
        for(int i=0;i<10;i++){
            fra = Expre.generateFraction(10);
            for(int j : fra){
                System.out.print(j);
            }
            System.out.println("");
            Expre.getProperFraction(fra);
            for (int k : fra){
                System.out.print(k);
                System.out.println("");
            }
        }
    }


}