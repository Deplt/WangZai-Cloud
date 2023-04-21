package com.wangzai.common.redis.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
@AutoConfigureBefore(RedisAutoConfiguration.class) //避免spring自带的redisTemplate先注入到IOC
public class RedisConfig {

    @Value("${redisson.max.connectionSize:128}")
    private Integer connectionPoolSize;

    @Value("${redisson.scanInterval:2000}")
    private Integer scanInterval;

    @Value(("${spring.redis.cluster.nodes:}"))
    private String redisNodes;

    @Value("${spring.redis.cluster.password:}")
    private String password;

    private String prefix = "redis://";

    @Value("${redisson.master.connection.minimum.idle.size:10}")
    private Integer masterIdleSize;

    @Value("${redisson.slave.connection.minimum.idle.size.10}")
    private Integer slaveIdleSize;

    @Bean
    @ConditionalOnMissingBean(RedisSerializer.class)
    public RedisSerializer<Object> redisSerializer() {
        return new JdkSerializationRedisSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> redisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return null;
    }

    @Bean
    @ConditionalOnProperty(prefix = "wangzai.redisson", name = "enabled", matchIfMissing = true)
    public RedissonClient redissonClient() {
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        List<String> nodes = StrUtil.split(redisNodes, StrUtil.C_COMMA).stream().map(str -> StrUtil.addPrefixIfNot(str, prefix)).collect(Collectors.toList());
        clusterServersConfig.setNodeAddresses(nodes);
        clusterServersConfig.setMasterConnectionMinimumIdleSize(masterIdleSize);
        clusterServersConfig.setSlaveConnectionMinimumIdleSize(slaveIdleSize);
        if (StrUtil.isNotBlank(password)) {
            clusterServersConfig.setPassword(password);
        }
        clusterServersConfig
                // 主节点变化扫描间隔时间
                .setScanInterval(scanInterval)
                .setSlaveConnectionPoolSize(connectionPoolSize)
                .setMasterConnectionPoolSize(connectionPoolSize);

        return Redisson.create(config);
    }
}
