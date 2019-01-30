package com.dany.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yindan on 2019/1/9.
 */

public class TimeCost {
    public static Map<String, Long> sStartTime = new HashMap<>();
    public static Map<String, Long> sEndTime = new HashMap<>();

    public static void setStartTime(String name) {
        System.out.println("setStartTime method="+name+", "+System.currentTimeMillis());
        sStartTime.put(name, System.currentTimeMillis());
    }

    public static void setEndTime(String name) {
        long start = sStartTime.get(name);
        long end = System.currentTimeMillis();
        if (Long.valueOf(end - start) > 1.0){
            System.out.println("=======COST:  method: " +name+ " cost " + Long.valueOf(end - start) + " ms");
        }
    }



}
