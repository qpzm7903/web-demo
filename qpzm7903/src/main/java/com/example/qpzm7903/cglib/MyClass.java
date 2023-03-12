package com.example.qpzm7903.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyClass {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallback(new MyMethodInterceptor());
        MyClass myClass = (MyClass) enhancer.create();
        myClass.setName("John");
        System.out.println(myClass.getName());
    }

    public static class MyMethodInterceptor implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("Before method " + method.getName());
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("After method " + method.getName());
            return result;
        }
    }
}
