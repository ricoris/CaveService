package dk.msdo.caveservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.msdo.caveservice.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    @Autowired
    Environment environment;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    @ConditionalOnProperty(value = "storage.room",
            havingValue = "redisStorage")
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(host);
        redisConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisConfiguration);
    }

    /**
     * Load Redis room repository if redisStorage is in active profiles
     * <p>
     * Spring boot as default considers Redis DB as a CacheManager. This means the default is to use CacheManager
     * serialization meaning that keys in Redis DB will include class/method signatures, which we do not want. Hence
     * we must setup our own serialization.
     */
    @Bean
    @ConditionalOnProperty(value = "storage.room",
            havingValue = "redisStorage")
    public RedisTemplate<String, Room> roomTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Room> roomTemplate = new RedisTemplate<>();
        roomTemplate.setConnectionFactory(connectionFactory);

        // Construct the serializer
        //Turn on the default type
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //Set date format

        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Jackson2JsonRedisSerializer<? extends Room> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Room.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // Attach serializer to the template to avoid caching behaviour for key, value sets.
        roomTemplate.setKeySerializer(new StringRedisSerializer());
        roomTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // Attach serializer to the template to avoid caching behaviour for Hash sets.
        roomTemplate.setHashKeySerializer(new StringRedisSerializer());
        roomTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

//        HashOperations<String, String, Room> roomOperations;
        return roomTemplate;
    }

    @Bean
    InfoContributor getInfoContributor() {
        Map<String, Object> details = new HashMap<>();
        details.put("GitHub", "https://github.com/b33rsledge/CaveService");
        details.put("DockerHub", "https://hub.docker.com/repository/docker/b33rsledge/caveservice/");
        details.put("DockerHubTag", "v2");
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("MSDO", details);

        Map<String, Object> serverDetails = new HashMap<>();
        try {
            serverDetails.put("name", InetAddress.getLocalHost().getHostName());
            serverDetails.put("address", InetAddress.getLocalHost().getHostAddress());
            serverDetails.put("port", environment.getProperty("server.port"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        wrapper.put("server", serverDetails);

        Map<String, Object> authorDetails = new HashMap<>();
        authorDetails.put("ave@bec.dk", "Anton Vestergaard");
        authorDetails.put("ris@bec.dk", "Rico Sørensen");
        authorDetails.put("phg@bec.dk", "Peter Højbjerg");
        wrapper.put("Authors", authorDetails);

        return new MapInfoContributor(wrapper);
    }
}