package com.hh.dao.impl;

import com.hh.dao.IExpressionDao;
import com.hh.entity.Expression;
import com.hh.entity.Pattern;

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
    private Random rand = new Random();

    public ExpressionDaoImpl() {
        super();
    }

    @Override
    public Expression generateExpression(int range) {

        return null;
    }

    @Override
    public Integer[] generateNaturalNum(int range) {

        Integer[] naturalNum = new Integer[2];
        naturalNum[0] = 0;
        naturalNum[1] = rand.nextInt(range);
        return naturalNum;
    }

    @Override
    public Integer[] generateFraction(int range) {
        Integer[] fraction = new Integer[4];
        fraction[0] = 1;
        fraction[1] = rand.nextInt(range);
        fraction[3] = rand.nextInt(range-2)+2;
        //分子必须比分母小
        fraction[2] = rand.nextInt(fraction[3]-1)+1;
        //调用方法化简为真分数
        return getProperFraction(fraction);
    }

    @Override
    public Integer[] getProperFraction(Integer[] fraction) {
        int m,n,rem;
        fraction[1] += fraction[2]/fraction[3];
        fraction[2] %= fraction[3];
        m = fraction[3];
        n = fraction[2];
        while(n>0){
            rem = m % n;
            m = n;
            n = rem;
        }
        fraction[2] /=m;
        fraction[3] /=m;
        return fraction;
    }

    @Override
    public String generateOperator() {
        String[] ope = new String[]{"+","-","*","/"};
        return ope[rand.nextInt(4)];
    }

    @Override
    public String generatePattern() {
        int temp = rand.nextInt(14);
        return Pattern.patternMap.get(temp);
    }

}
