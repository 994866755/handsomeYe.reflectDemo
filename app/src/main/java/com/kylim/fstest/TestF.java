package com.kylim.fstest;

import android.util.Log;

public class TestF {

    private static volatile TestF testF;

    public static TestF getInstance() {
        if (testF == null) {
            synchronized (TestF.class) {
                if (testF == null) {
                    testF = new TestF();
                }
            }
        }
        return testF;
    }

    public void testOne(){
        Log.v("testOne", "调用testOne");
    }

}
