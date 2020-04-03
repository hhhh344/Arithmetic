package com.hh.dao.impl;

import com.hh.dao.IExpressionDao;
import com.hh.entity.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 戮漠
 *
 * 对表达式进行操作
 */
public class ExpressionDaoImpl implements IExpressionDao {

    List<Expression> expressions = new ArrayList<Expression>();

    public ExpressionDaoImpl() {
        super();
    }

    @Override
    public Expression generateExpression(int range) {
        return null;
    }

    @Override
    public Integer[] generateNaturalNum(int range) {
        Random rand = new Random();
        Integer[] naturalNum = new Integer[2];
        naturalNum[0] = 0;
        naturalNum[1] = rand.nextInt(range);
        return naturalNum;
    }

    @Override
    public Integer[] generateFraction(int range) {
        return new Integer[0];
    }

    @Override
    public String generateOperator() {
        return null;
    }

    @Override
    public String generatePattern() {
        return null;
    }
}
