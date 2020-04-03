package com.hh.dao;

/**
 * @author 戮漠
 *对表达式的操作的接口
 */
public interface IExpressionDao {
    /**
     * 唯一能调用的方法
     */
    public String generateExpression(int range);
}
