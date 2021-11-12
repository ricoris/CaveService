package dk.msdo.caveservice;

import dk.msdo.caveservice.domain.Exit;
import dk.msdo.caveservice.domain.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
public class Configuration {

    @Value("${spring.redis.host}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private int redisPort;

    //Creating Connection to Redis DB
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHostName, redisPort));
    }

    //Creating RedisTemplate for Room
    @Bean
    public RedisTemplate<String, Room> roomTemplate(){
        RedisTemplate<String, Room> roomTemplate = new RedisTemplate<>();
        roomTemplate.setConnectionFactory(redisConnectionFactory());
        return roomTemplate;
    }

    //Creating RedisTemplate for Exits
    @Bean
    public RedisTemplate<String, Exit> exitsTemplate(){
        RedisTemplate<String, Exit> exitsTemplate = new RedisTemplate<>();
        exitsTemplate.setConnectionFactory(redisConnectionFactory());
        return exitsTemplate;
    }
}