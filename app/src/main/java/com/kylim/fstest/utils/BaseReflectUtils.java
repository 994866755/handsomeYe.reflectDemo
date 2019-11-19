package com.kylim.fstest.utils;

import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseReflectUtils {

    /**
     *  获取类对象
     */
    public static Class getClass(String clsName){
        try {
            if (!TextUtils.isEmpty(clsName)){
                Class<?> cls = Class.forName(clsName);
                return cls;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  反射调用构造方法
     */
    public static Object getConstructor(Class cls, Class[] paramsType, Object[] params){
        Object result = null;
        try {
            if (cls != null){
                Constructor constructor = cls.getConstructor(paramsType);
                result = constructor.newInstance(params);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  反射属性
     */
    public static Object getField(Class cls, String fname){
        return getField(cls, fname, null);
    }

    public static Object getField(Class cls, String fname, Object obj){
        Object result = null;
        try {
            Field daf = cls.getField(fname);
            // 反射静态参数直接传空就行，反射非静态参数要使用相对应的对象
            result = daf.get(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  反射方法
     */
    // 静态无参
    public static Object getMethod(Class cls, String mname){
        return getMethod(cls, mname, null, null, null);
    }

    // 静态有参
    public static Object getMethod(Class cls, String mname, Class[] paramsType, Object[] params){
        return getMethod(cls, mname, paramsType, params, null);
    }

    // 非静态无参
    public static Object getMethod(Class cls, String mname, Object obj){
        return getMethod(cls, mname, null, null, obj);
    }

    public static Object getMethod(Class cls, String mname, Class[] paramsType, Object[] params, Object obj){
        Object object = null;
        try {
            Method method = cls.getMethod(mname, paramsType);
            object = method.invoke(obj, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    /**
     *  链式调用
     */
    public static class Builder{

        private Class cls;
        private String mname;
        private Class[] paramsType;
        private Object[] params;
        private Object obj;

        public Builder setCls(Class cls) {
            this.cls = cls;
            return this;
        }

        public Builder setMname(String mname) {
            this.mname = mname;
            return this;
        }

        public Builder setParamsType(Class[] paramsType) {
            this.paramsType = paramsType;
            return this;
        }

        public Builder setParams(Object[] params) {
            this.params = params;
            return this;
        }

        public Builder setObj(Object obj) {
            this.obj = obj;
            return this;
        }

        public Object build(){
            // 类名和方法名不能为空
            if (cls == null || TextUtils.isEmpty(mname)){
                return null;
            }

            return getMethod(cls, mname, paramsType, params, obj);
        }

    }

}
