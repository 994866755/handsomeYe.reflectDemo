package com.kylim.fstest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kylim.fstest.utils.BaseReflectUtils;
import com.kylim.fstest.utils.KlReflectUtils;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnE;
    private Button btnF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btnA = findViewById(R.id.btn_a);
        btnB = findViewById(R.id.btn_b);
        btnC = findViewById(R.id.btn_c);
        btnD = findViewById(R.id.btn_d);
        btnE = findViewById(R.id.btn_e);
        btnF = findViewById(R.id.btn_f);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a:
                clickA(2);
                break;
            case R.id.btn_b:
                clickB(0);
                break;
            case R.id.btn_c:
                clickC(2);
                break;
            case R.id.btn_d:
                clickD();
                break;
            case R.id.btn_e:
                clickE();
                break;
            case R.id.btn_f:
                clickF();
                break;
        }
    }

    /**
     *  构造方法
     * @param type 几个参数的构造方法
     */
    private void clickA(int type){
        Class cls = BaseReflectUtils.getClass("com.kylim.fstest.TestB");
        if (type == 0){
            BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]);
        }else if (type == 1){
            Class[] clsA = new Class[]{String.class};
            Object[] objA = new Object[]{"参数1"};
            BaseReflectUtils.getConstructor(cls, clsA, objA);
        }else if (type == 2){
            Class[] clsA = new Class[]{String.class, int.class};
            Object[] objA = new Object[]{"参数1", 0};
            BaseReflectUtils.getConstructor(cls, clsA, objA);
        }else {
            // 不存在对应的构造方法
        }

    }

    /**
     *  静态属性
     * @param type 0：普通参数  1：静态参数  2：常量
     */
    private void clickB(int type){
        Class cls = BaseReflectUtils.getClass("com.kylim.fstest.TestC");
        if (type == 0){
//            Object obj = BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]);
            Object obj = null;
            try {
                obj = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            int one = (int) BaseReflectUtils.getField(cls, "dataOne", obj);
            Log.v("MainActivity", one+"");
        }else if (type == 1){
            String two = (String) BaseReflectUtils.getField(cls, "dataTwo");
            Log.v("MainActivity", two+"");
        }else if (type == 2){
            boolean three = (boolean) BaseReflectUtils.getField(cls, "dataThree");
            Log.v("MainActivity", three+"");
        }
    }

    /**
     *  方法
     * @param type 从0开始对应TestD的列举的方法的顺序
     */
    private void clickC(int type){
        Class cls = BaseReflectUtils.getClass("com.kylim.fstest.TestD");
        if (type == 0){
            Object obj = BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]);
            int result = (int) BaseReflectUtils.getMethod(cls, "testOne", obj);
            Log.v("MainActivity", "方法1结果： "+result);
        }else if (type == 1){
            Object obj = BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]);
            Class[] clsA = new Class[]{String.class};
            Object[] objA = new Object[]{"参数1"};
            BaseReflectUtils.getMethod(cls, "testOne", clsA, objA, obj);
        }else if (type == 2){
//            Object obj = BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]);
//            Class[] clsA = new Class[]{int.class};
//            Object[] objA = new Object[]{101};
//            String result = (String) BaseReflectUtils.getMethod(cls, "testTwo", clsA, objA, obj);

            String result = (String) new BaseReflectUtils.Builder().setCls(cls)
                    .setMname("testTwo")
                    .setParamsType(new Class[]{int.class})
                    .setParams(new Object[]{101})
                    .setObj(BaseReflectUtils.getConstructor(cls,new Class[0], new Object[0]))
                    .build();

            Log.v("MainActivity", "方法3结果： "+result);
        }else if (type == 3){
            int result = (int) BaseReflectUtils.getMethod(cls, "testThree");
            Log.v("MainActivity", "方法4结果： "+result);
        }else if (type == 4){
            Class[] clsA = new Class[]{boolean.class};
            Object[] objA = new Object[]{true};
            BaseReflectUtils.getMethod(cls, "testThree", clsA, objA);
        }
    }

    /**
     *  子类
     */
    private void clickD(){
        try {
            Class cls = KlReflectUtils.getChildClass("com.kylim.fstest.TestE", "ChildTestE");
            Object obj = BaseReflectUtils.getConstructor(cls, new Class[0], new Object[0]);
//            Object obj = clsList[0].newInstance();
            Class[] clsA = new Class[]{int.class};
            Object[] objA = new Object[]{666};
            BaseReflectUtils.getMethod(cls, "testTwo", clsA, objA, obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  单例
     */
    private void clickE(){
        try {
            Class cls = BaseReflectUtils.getClass("com.kylim.fstest.TestF");
            BaseReflectUtils.getMethod(cls, "testOne", BaseReflectUtils.getMethod(cls, "getInstance"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  Builder模式
     */
    private void clickF(){
        Class cls = KlReflectUtils.getChildClass("com.kylim.fstest.TestG", "Builder");
        // 构造方法
        Object obj = BaseReflectUtils.getConstructor(cls, new Class[0], new Object[0]);

//        // 调用第一个方法
//        Class[] clsA = new Class[]{int.class};
//        Object[] objA = new Object[]{200};
//        Object obj1 =  BaseReflectUtils.getMethod(cls, "setCode", clsA, objA, obj);
//        // 调用第二个方法
//        Class[] clsB = new Class[]{String.class};
//        Object[] objB = new Object[]{"请求成功"};
//        Object obj2 =  BaseReflectUtils.getMethod(cls, "setMsg", clsB, objB, obj1);
//        // 调用第三个方法
//        BaseReflectUtils.getMethod(cls, "build", obj2);

        new KlReflectUtils.Builder(cls, obj)
                .setBuilderData( "setCode", new Class[]{int.class}, new Object[]{200})
                .setBuilderData( "setMsg", new Class[]{String.class}, new Object[]{"请求成功"})
                .build();

    }

}
