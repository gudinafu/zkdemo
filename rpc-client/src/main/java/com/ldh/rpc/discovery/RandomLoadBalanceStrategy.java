package com.ldh.rpc.discovery;

import java.util.List;
import java.util.Random;

public class RandomLoadBalanceStrategy extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> repos) {
        int length = repos.size();
        Random random = new Random();//从repos集合中随机获取一个地址
        return repos.get(random.nextInt(length));
    }
}
