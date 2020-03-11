package com.ldh.rpc.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class RegistryCentreWithZK implements RegistryCentre {


    private CuratorFramework curatorFramework;

    {
        //初始化ZK的连接
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("registry")
                .build();

        curatorFramework.start();
    }

    @Override
    public void registry(String serviceName, String serviceAddress) {
        String servicePath = "/" + serviceName;
        try {

            //判断节点是否存在
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                //如果不存在则创建一个持久化节点
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath);
            }
            String addressPath = servicePath + "/" + serviceAddress;
            //创建临时节点?(服务节点发生变化后,需要删除掉)
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
            System.out.println("服务注册成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
