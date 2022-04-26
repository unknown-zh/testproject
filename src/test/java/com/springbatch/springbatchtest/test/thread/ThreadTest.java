package com.springbatch.springbatchtest.test.thread;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadTest.java
 * @PackagePath com.springbatch.springbatchtest.test.thread
 * @Author Dr. zhang
 * @CreateTime 2022/4/18
 * @Description
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class ThreadTest {

    @SpringBootTest
    public static class MyThread1 extends Thread {
        //创建一个线程任务
        @Override
        public void run() {
            super.run();
            System.out.println("111");
        }

        @Test
        public void aaaaaaa() {
            Thread t = new MyThread1();
            System.out.println("main");
            t.start();
        }
    }

    static class NameTheadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NameTheadFactory() {
            //默认namePrefix = default-name-pool
            this("default-name-pool");
        }

        NameTheadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            //此时namePrefix就是 name + 第几个用这个工厂创建线程池的
            this.namePrefix = name +
                    poolNumber.getAndIncrement();
        }

        public Thread newThread(Runnable r) {
            //此时线程的名字 就是 namePrefix + -thread- + 这个线程池中第几个执行的线程
            Thread t = new Thread(group, r,
                    namePrefix + "-thread-" + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


    @SpringBootTest
    public static class MyThread2 implements Runnable {
        //构造方法
        ThreadPoolExecutor tpe1 = new ThreadPoolExecutor(10, 15, 1000,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),
                new NameTheadFactory("zhangmt"), new ThreadPoolExecutor.AbortPolicy());

        //对象，可能有问题
        ExecutorService tpe2 = Executors.newFixedThreadPool(10);


        @Test
        public void aaaaaaa() throws InterruptedException {

//            Thread t = new Thread(this);
//            //当前线程，普通方法
//            t.run();
//            //新线程
//            t.start();
//            t.interrupt();

            //无返回值
            tpe1.execute(new MyThread2());
            tpe2.execute(new MyThread2());
            //有返回值
            Future<?> submit = tpe1.submit(this);

            //放一下
            tpe1.getQueue().add(this);
            //自旋一直放
            tpe1.getQueue().put(this);

            System.out.println(tpe1.getTaskCount());

        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("111");
        }
    }


    public static class MyThread3 implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("111");
            return null;
        }

        @Test
        public void aaa() throws ExecutionException, InterruptedException {
            MyThread3 t = new MyThread3();
            FutureTask f = new FutureTask(t);
            new Thread(f).start();
            Object o = f.get();
            System.out.println(o);
        }
    }


}
