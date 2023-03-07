package com.wangzai.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单配置
 */
@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "security.white")
public class WhiteUrlPoperties {

    private List<String> whiteUrls = new ArrayList<>();
}
