package com.dany.timecost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dany.mysdk.MainSdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        method1();
        method2();
        MainSdk sdk = new MainSdk();
        sdk.test();
    }

    private void method1() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
