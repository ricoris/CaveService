package dk.msdo.caveservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.msdo.caveservice.domain.Exit;
import dk.msdo.caveservice.domain.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
public class Configuration {

    //Creating RedisTemplate for Room
    @Bean
    public RedisTemplate<String, Room> roomTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Room> roomTemplate = new RedisTemplate<>();
        roomTemplate.setConnectionFactory(connectionFactory);


        //Turn on the default type
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //Set date format
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Room.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // Set serializer to avoid caching behaviour
        roomTemplate.setKeySerializer(new StringRedisSerializer());
        roomTemplate.setHashKeySerializer(new StringRedisSerializer());

        roomTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        roomTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return roomTemplate;
    }


    //Creating RedisTemplate for Exits
    @Bean
    public RedisTemplate<String, Exit> exitsTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Exit> exitsTemplate = new RedisTemplate<>();
        exitsTemplate.setConnectionFactory(connectionFactory);

        // Set serializer to avoid caching behaviour
        exitsTemplate.setKeySerializer(new StringRedisSerializer());
        exitsTemplate.setValueSerializer(new StringRedisSerializer());

        exitsTemplate.setHashValueSerializer(new StringRedisSerializer());
        exitsTemplate.setHashKeySerializer(new StringRedisSerializer());

        return exitsTemplate;
    }
}