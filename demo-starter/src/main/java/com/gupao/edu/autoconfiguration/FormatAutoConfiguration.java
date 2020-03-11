package com.gupao.edu.autoconfiguration;


import com.gupao.edu.format.FormatProcessor;
import com.gupao.edu.format.JsonFormatProcessor;
import com.gupao.edu.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FormatAutoConfiguration {

    /**
     * @Primary 告诉spring 在犹豫的时候优先选择哪一个具体的实现
     */
    @Bean
    @Primary
    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    public FormatProcessor StringFormat() {
        return new StringFormatProcessor();
    }

    @Bean
    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    public FormatProcessor JsonFormat() {
        return new JsonFormatProcessor();
    }

}
