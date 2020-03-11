package com.gupao.edu.autoconfiguration;

import com.gupao.edu.template.FormatTemplate;
import com.gupao.edu.format.FormatProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@Import(FormatAutoConfiguration.class)
public class HelloAutoConfiguration {

    @Bean
    public FormatTemplate HelloFormatTemplate(HelloProperties helloProperties,FormatProcessor formatProcessor){
        return new FormatTemplate(helloProperties,formatProcessor);
    }

}
