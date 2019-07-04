# TimeCostPlugin

该插件实现功能：在所有的方法的第一行及最后一行插入一行代码

    public void setTheme(int i) {
        TimeCost.setStartTime("com/example/dany/myapplication/CostTimeActivity_setTheme");
        super.setTheme(i);
        Log.i("test", "setTheme");
        TimeCost.setEndTime("com/example/dany/myapplication/CostTimeActivity_setTheme");
    }

    protected void onPause() {
        TimeCost.setStartTime("com/example/dany/myapplication/CostTimeActivity_onPause");
        super.onPause();
        KotlinInstace.Companion.getInstance().setA(false);
        TimeCost.setEndTime("com/example/dany/myapplication/CostTimeActivity_onPause");
    }

    private void OutMethod() {
        TimeCost.setStartTime("com/example/dany/myapplication/CostTimeActivity_OutMethod");
        InnerMethod1();
        InnerMethod2();
        InnerMethod3();
        TimeCost.setEndTime("com/example/dany/myapplication/CostTimeActivity_OutMethod");
    }

如何使用：
project中:

buildscript {

  repositories {
  
     maven {
     
      url "https://plugins.gradle.org/m2/"
      
    }
  }
  dependencies {  
  
    classpath "gradle.plugin.com.dany.timeplugin:timeplugin:1.0.1"
    
    }
 }

app中：
apply plugin: "com.dany.timecost"

timecost {//默认是true，可以不配置这个。false时，只在app中插桩，不处理第三方库
    jarEnable false
}


新增如下类在自己的app目录中，包名，类名com.dany.util.TimeCost，方法名setStartTime，setEndTime不能变:

package com.dany.util;    //包路径名及类名不能变，否则插入代码失败

import java.util.HashMap;
import java.util.Map;

/**
* Created by dany on 2019/1/9.
*/

public class TimeCost {

    public static Map<String, Long> sStartTime = new HashMap<>();
    private static boolean isEnable = true;

    public static void setStartTime(String name) {
        if(isEnable) {
            String methodName = name.substring(name.lastIndexOf("_")+1);
            String className = name.substring(name.lastIndexOf("/")+1,name.lastIndexOf("_"));
            System.out.println("costtime start:  class: " +className +"  :  ["+methodName+"]");
        }
        sStartTime.put(name, System.currentTimeMillis());
    }

    public static void setEndTime(String name) {
        long start = sStartTime.get(name);
        long end = System.currentTimeMillis();
        try {
            if (isEnable && Long.valueOf(end - start) > 1.0){
                String methodName = name.substring(name.lastIndexOf("_")+1);
                String className = name.substring(name.lastIndexOf("/")+1,name.lastIndexOf("_"));
                System.out.println("costtime end:  class: " +className +"  :  ["+methodName+"] "+ " cost " + Long.valueOf(end - start) + " ms"+", thread="+Thread.currentThread().getId());
            }
        }catch (Exception e){
            System.out.println("costtime end:  name: " +name+ " cost " + Long.valueOf(end - start) + " ms");
        }

    }

    public static void setEnable(boolean enable) {
        isEnable = enable;
    }

}

如上类打印log如下：
I/System.out: costtime end:  class: TestApplication  :  [onCreate]  cost 3 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [setTheme]  cost 3 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [InnerMethod1]  cost 100 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [InnerMethod2]  cost 201 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [InnerMethod3]  cost 300 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [OutMethod]  cost 601 ms, thread=1

I/System.out: costtime end:  class: CostTimeActivity  :  [onCreate]  cost 671 ms, thread=1

功能及用途：

1.可用于查看某个时间段执行的所有类名 方法名

2.可用于测试app启动过程中冷启动 热启动的时长，及每个方法的执行时间

3.可在任意方法执行时，插入某些功能代码
