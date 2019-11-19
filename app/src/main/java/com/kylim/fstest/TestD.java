package com.kylim.fstest;

import android.util.Log;

public class TestD {

    // 非静态无参
    public int testOne(){
        return 250;
    }

    // 非静态有参
    public void testOne(String str){
        Log.v("TestD", "testOne  "+str);
    }

    // 非静态有参有返回
    public String testTwo(int data){
        if (data > 100){
            return "100以上";
        }
        return "100或100以下";
    }

    // 静态无参
    public static int testThree(){
        return 666;
    }

    // 静态有参
    public static void testThree(boolean data){
        if (data){
            Log.v("TestD" , "正确");
        }else {
            Log.v("TestD" , "错误");
        }
    }

}
