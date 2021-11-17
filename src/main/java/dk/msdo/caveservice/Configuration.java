package dk.msdo.caveservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.msdo.caveservice.configuration.MemoryStorageCondition;
import dk.msdo.caveservice.configuration.RedisDBStorageCondition;
import dk.msdo.caveservice.configuration.StorageConfiguration;
import dk.msdo.caveservice.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private final Environment env;

    public Configuration(Environment env) {
        this.env = env;
    }

    /**
     * Load Redis room repository if redisStorage is in active profiles
     *
     * Spring boot as default considers Redis DB as a CacheManager. This means the default is to use CacheManager
     serialization meaning that keys in Redis DB will include class/method signatures, which we do not want. Hence
     we must setup our own serialization.
     */
    @Bean
    @Conditional(RedisDBStorageCondition.class)
    public RedisTemplate<String, Room> roomTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Room> roomTemplate = new RedisTemplate<>();
        roomTemplate.setConnectionFactory(connectionFactory);

        // Construct the serializer
        //Turn on the default type
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //Set date format
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Room.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // Attach serializer to the template to avoid caching behaviour for key, value sets.
        roomTemplate.setKeySerializer(new StringRedisSerializer());
        roomTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // Attach serializer to the template to avoid caching behaviour for Hash sets.
        roomTemplate.setHashKeySerializer(new StringRedisSerializer());
        roomTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return roomTemplate;
    }
}