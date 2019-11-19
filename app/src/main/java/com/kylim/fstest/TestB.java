package com.kylim.fstest;

import android.util.Log;

public class TestB {

    public TestB(){
        Log.v("TestB", "调用无参构造方法");
    }

    public TestB(String data){
        Log.v("TestB","调用1参数构造方法  "+data);
    }

    public TestB(String data, int code){
        Log.v("TestB","调用2参数构造方法  "+data+"  "+code);
    }

}
