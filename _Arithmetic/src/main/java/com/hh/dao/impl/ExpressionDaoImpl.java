package com.hh.dao.impl;

import com.hh.dao.IExpressionDao;

/**
 * @author 戮漠
 * 对表达式进行操作
 */
public class ExpressionDaoImpl implements IExpressionDao {

    @Override
    public String generateExpression(int number, int range) {
        System.out.println("number="+number + "range" + range);
        System.out.println("hhtgood！！！！！！！");
        return "number="+number + "range=" + range;
    }
}
