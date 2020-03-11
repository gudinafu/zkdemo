package com.gupao.edu.template;

import com.gupao.edu.autoconfiguration.HelloProperties;
import com.gupao.edu.format.FormatProcessor;

public class FormatTemplate {

    private FormatProcessor formatProcessor;
    private HelloProperties helloProperties;

    public FormatTemplate(HelloProperties helloProperties, FormatProcessor formatProcessor) {
        this.formatProcessor = formatProcessor;
        this.helloProperties = helloProperties;
    }

    public <T> String format(T obj) {
        return formatProcessor.foamat(obj) + helloProperties.getInfo().toString();
    }
}
