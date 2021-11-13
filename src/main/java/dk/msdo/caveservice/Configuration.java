package dk.msdo.caveservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.msdo.caveservice.domain.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
public class Configuration {

    //Creating Connection to Redis DB
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    //Creating RedisTemplate for Room
    @Bean
    public RedisTemplate<String, Room> roomTemplate(){
        RedisTemplate<String, Room> roomTemplate = new RedisTemplate<>();
        roomTemplate.setConnectionFactory(redisConnectionFactory());

        //Turn on the default type
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //Set date format
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Room.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // Set serializer to avoid caching behaviour for key, value sets
        roomTemplate.setKeySerializer(new StringRedisSerializer());
        roomTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // Set serializer to avoid caching behaviour for Hash sets
        roomTemplate.setHashKeySerializer(new StringRedisSerializer());
        roomTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return roomTemplate;
    }
}