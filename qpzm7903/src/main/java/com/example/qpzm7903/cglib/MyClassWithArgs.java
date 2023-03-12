package com.example.qpzm7903.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyClassWithArgs {
    private String name;

    public MyClassWithArgs() {
        System.out.println("Constructing MyClass...");
    }

    public MyClassWithArgs(String name) {
        this.name = name;
        System.out.println("Constructing MyClass with name: " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClassWithArgs.class);
        enhancer.setCallback(new MyMethodInterceptor());
        MyClassWithArgs myClass = (MyClassWithArgs) enhancer.create(new Class[]{String.class}, new Object[]{"John"});
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
