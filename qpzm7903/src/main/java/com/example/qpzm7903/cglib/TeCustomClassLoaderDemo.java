package com.example.qpzm7903.cglib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TeCustomClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader classLoader = new CustomClassLoader();
        Class<?> clazz = classLoader.loadClass("HelloWorld");
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("sayHello");
        method.invoke(obj);
    }

    static class CustomClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadClassData(name);
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadClassData(String name) throws IOException {
            String fileName = name.replace(".", "/") + ".class";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();
        }
    }
}


