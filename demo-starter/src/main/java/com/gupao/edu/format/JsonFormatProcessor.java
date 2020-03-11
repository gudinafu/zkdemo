package com.gupao.edu.format;

import java.util.Objects;

public class JsonFormatProcessor implements FormatProcessor {
    @Override
    public <T> String foamat(T obj) {
        return "JsonFormatProcessor: " + Objects.toString(obj);
    }
}
