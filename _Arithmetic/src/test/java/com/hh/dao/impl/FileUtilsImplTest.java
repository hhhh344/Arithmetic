package com.hh.dao.impl;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsImplTest {

    FileUtilsImpl fui = new FileUtilsImpl();
    ExpressionDaoImpl exp = new ExpressionDaoImpl();

    @Test
    void createNewFile() throws IOException {
        fui.createNewFile("test1.txt");
    }

    @Test
    void writeExpressionInFile() throws IOException {
        exp.generateMultiExpression(10, 3);
        File file1 = fui.createNewFile("test1.txt");
        File file2 = fui.createNewFile("test2.txt");
        System.out.println(fui.writeExpressionInFile(file1, exp.expressions));
        System.out.println(fui.writeAnswerInFile(file2, exp.expressions));
    }

    @Test
    void writeAnswerInFile() {

    }
}