package com.akfc.training.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("shared")
@RefreshScope
@Data
public class MessagesConfig {

    private String greetings;

}
