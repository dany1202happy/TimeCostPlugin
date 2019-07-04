package com.example.dany.mysdk;

import android.os.HandlerThread;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainSdk {

    public void test() {
        Log.i("dany","mainsdk test()");
        testThread();
        testHandleThread();
        testExecutors();
    }

    private void testExecutors() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                }
            });

        }
    }

    private void testHandleThread() {
        HandlerThread handlerThread = new HandlerThread("Handler Thread");
        handlerThread.start();
        Log.i("dany",""+handlerThread.getName());

    }

    private void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("dany","mythread test");
            }
        }).start();
    }


}
