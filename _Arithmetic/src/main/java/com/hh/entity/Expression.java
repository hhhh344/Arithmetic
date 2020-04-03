package com.hh.entity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 戮漠
 */
public class Expression {

    // 参数列表
    private List<Map<Integer, Integer>> parameterList;
    // 操作符列表
    private List<String> operatorList;
    // 运算结果
    private String result;
    // 括号类型
    private int bracketType;
}
