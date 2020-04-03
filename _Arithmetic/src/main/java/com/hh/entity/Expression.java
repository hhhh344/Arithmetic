package com.hh.entity;

import java.util.List;

/**
 *表达式定义
 * @author 戮漠
 */
public class Expression {

    /**
     * parameterList 操作数列表
     */
    private List<Integer[]> parameterList;

    /**
     * operatorList 操作符列表
     */
    private List<String> operatorList;

    /**
     * result 运算结果
     */
    private String result;

    /**
     * pattern 表达式模型
     */
    private String pattern;


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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
