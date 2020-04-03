package com.hh.entity;

import java.util.List;
import java.util.Map;

/**
 *表达式定义
 * @author 戮漠
 */
public class Expression {

    // 操作数列表
    private List<Integer[]> parameterList;
    // 操作符列表
    private List<String> operatorList;
    // 运算结果
    private String result;
    // 表达式模型
    private int pattern;

    public List<Integer[]> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Integer[]> parameterList) {
        this.parameterList = parameterList;
    }

    public List<String> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<String> operatorList) {
        this.operatorList = operatorList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPattern() {
        return pattern;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }
}
