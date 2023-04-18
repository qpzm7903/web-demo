package com.example.swagger2demo;

import java.util.concurrent.*;

public class ThreadLocalDemo {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static ExecutorService executorService = new ThreadPoolExecutor(10, 10, 100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));

    public static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        private String localName;

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            String s = threadLocal.get();
            System.out.printf("this is beforeExecute, " + s);
            this.localName = s;
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }
    }

    public static ExecutorService myThreadPoolExecutor = new MyThreadPoolExecutor(10, 10, 100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("test");
        System.out.println(threadLocal.get());

        Runnable runnable = () -> {
            System.out.println(threadLocal.get());
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();

        executorService.execute(runnable);
        executorService.shutdown();


        myThreadPoolExecutor.execute(runnable);
        myThreadPoolExecutor.shutdown();
    }
}
