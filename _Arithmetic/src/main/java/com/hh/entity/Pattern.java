package com.hh.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戮漠
 * 表达式的模型
 */
public class Pattern {

    public static final Map<Integer, String> patternMap = new HashMap<Integer, String>();

    static {
        patternMap.put(0, "n#n");
        patternMap.put(1, "n#n#n");
        patternMap.put(2, "(n#n)#n");
        patternMap.put(3, "n#(n#n)");
        patternMap.put(4, "n#n#n#n");
        patternMap.put(5, "(n#n)#n#n");
        patternMap.put(6, "n#(n#n)#n");
        patternMap.put(7, "n#n#(n#n)");
        patternMap.put(8, "(n#n#n)#n");
        patternMap.put(9, "n#(n#n#n)");
        patternMap.put(10, "(n#n)#(n#n)");
        patternMap.put(11, "((n#n)#n)#n");
        patternMap.put(12, "(n#(n#n))#n");
        patternMap.put(13, "n#((n#n)#n)");
        patternMap.put(14, "n#(n#(n#n))");
    }

}
