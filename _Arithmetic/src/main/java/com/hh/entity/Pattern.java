package com.hh.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戮漠
 * 表达式的模型
 */
public class Pattern {

    public static final Map<Integer, String> PATTERN_MAP = new HashMap<Integer, String>();

    static {
        PATTERN_MAP.put(0, "n#n");
        PATTERN_MAP.put(1, "n#n#n");
        PATTERN_MAP.put(2, "(n#n)#n");
        PATTERN_MAP.put(3, "n#(n#n)");
        PATTERN_MAP.put(4, "n#n#n#n");
        PATTERN_MAP.put(5, "(n#n)#n#n");
        PATTERN_MAP.put(6, "n#(n#n)#n");
        PATTERN_MAP.put(7, "n#n#(n#n)");
        PATTERN_MAP.put(8, "(n#n#n)#n");
        PATTERN_MAP.put(9, "n#(n#n#n)");
        PATTERN_MAP.put(10, "(n#n)#(n#n)");
        PATTERN_MAP.put(11, "((n#n)#n)#n");
        PATTERN_MAP.put(12, "(n#(n#n))#n");
        PATTERN_MAP.put(13, "n#((n#n)#n)");
        PATTERN_MAP.put(14, "n#(n#(n#n))");
    }

}
