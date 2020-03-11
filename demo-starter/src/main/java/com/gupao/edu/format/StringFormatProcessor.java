package com.gupao.edu.format;

import com.alibaba.fastjson.JSON;

public class StringFormatProcessor implements FormatProcessor {
    @Override
    public <T> String foamat(T obj) {
        return "StringFormatProcessor: " + JSON.toJSONString(obj);
    }
}
