package com.ldh.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.server.DataNode;

public class LockDemo {

    public static void main(String[] args) {

        //初始化ZK的连接
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        curatorFramework.start();

        final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "尝试竞争锁");
                    lock.acquire();//阻塞竞争锁
                    System.out.println(Thread.currentThread().getName() + "成功获得锁");
                    Thread.sleep(40000);
                    lock.release();//释放锁
                    System.out.println(Thread.currentThread().getName() + "成功释放锁");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }, "Thread-" + i).start();
        }
    }


}
