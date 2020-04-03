package com.hh.dao.impl;

import com.hh.dao.IExpressionDao;
import com.hh.entity.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戮漠
 *
 * 对表达式进行操作
 */
public class ExpressionDaoImpl implements IExpressionDao {

    List<Expression> expressions = new ArrayList<Expression>();

    @Override
    public String generateExpression(int range) {
        return null;
    }


}
