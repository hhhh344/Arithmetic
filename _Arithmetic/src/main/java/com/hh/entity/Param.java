package com.hh.entity;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.LinkedList;
import java.util.List;

/**
 * 外部参数管理
 * @author 戮漠
 */


@EntityScan
public class Param {

    private int number;
    private int range;

    public Param() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

}
