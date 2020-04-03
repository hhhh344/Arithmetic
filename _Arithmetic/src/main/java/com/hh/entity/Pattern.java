package com.hh.entity;

import java.util.HashMap;
import java.util.Map;

public class Pattern {

    public static final Map<Integer, String> patternMap = new HashMap<Integer, String>();

    static {
        patternMap.put(0, "n#n");
        patternMap.put(1, "n#n#n");
    }

}
