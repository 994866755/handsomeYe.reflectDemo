package com.kylim.fstest.utils;

import android.text.TextUtils;

public class KlReflectUtils extends BaseReflectUtils {

    /**
     *  获取子类Class
     */
    public static Class getChildClass(String clsName, String childClsName){
        try {
            if (!TextUtils.isEmpty(clsName)){
                Class<?> cls = Class.forName(clsName);
                Class[] children = cls.getClasses();
                if (children != null && !TextUtils.isEmpty(childClsName)){
                    for (int i = 0; i < children.length; i++) {
                        if (childClsName.equals(children[i].getSimpleName())){
                            return children[i];
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Builder模式的反射
     */
    public static class Builder{

        private Class cls;
        private Object object;

        private String mname;
        private Class[] paramsType;
        private Object[] params;

        public Builder(Class cls, Object obj){
            this.cls = cls;
            this.object = obj;
        }

        public Builder setBuilderData(String mname, Class[] paramsType, Object[] params){
            object = BaseReflectUtils.getMethod(cls, mname, paramsType, params, object);
            return this;
        }

        public Object build(){
            return build(null, null);
        }

        public Object build(Class[] paramsType, Object[] params){
            return BaseReflectUtils.getMethod(cls, "build", paramsType, params, object);
        }

    }

}
