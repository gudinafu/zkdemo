package com.ldh.rpc.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

public class ServiceDiscovryWithZk implements IServiceDiscovery {

    private CuratorFramework curatorFramework;

    private List<String> serviceRepos = new ArrayList<>();

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
    public String serviceAddress(String serviceName) {
        String path = "/" + serviceName; //registry/com.ldh.rpc.IHelloService
        try {
            //获得path下的所有节点
            serviceRepos = curatorFramework.getChildren().forPath(path);
            //监听
            registryWatch(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //针对已有的服务地址做负载均衡
        AbstractLoadBalance loadBalance = new RandomLoadBalanceStrategy();
        return loadBalance.doSelect(serviceRepos);
    }

    //监听
    private void registryWatch(final String path) throws Exception {
        PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener nodeCacheListener = (curatorFramework,pathChildrenCacheEvent)->
            serviceRepos = curatorFramework.getChildren().forPath(path);

        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
