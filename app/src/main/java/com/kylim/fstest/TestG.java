package com.kylim.fstest;

import android.util.Log;

public class TestG {

    private TestG(){

    }

    private TestG(Builder builder){
        Log.v("TestG", "结果："+builder.code+"  "+builder.msg);
    }

    public static class Builder{

        private int code;
        private String msg;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public TestG build(){
            return new TestG(this);
        }

    }

}
