package br.com.barbershop.config;

import br.com.barbershop.model.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${app.redis.url}")
    private String redisUrl;

    @Value("${app.redis.port}")
    private String redisPort;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        int port = Integer.parseInt(redisPort);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisUrl, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, Client> redisTemplate() {
        RedisTemplate<String, Client> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
