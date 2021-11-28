package dk.msdo.caveservice;


import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class CaveServiceApplicationTests {
    public static final int SERVER_PORT = 8080;
    public static final int SERVER_REDIS_PORT = 6379;
    public static final String REDIS_NETWORKNAME = "redisdb";
    private String serverRootUrl;
    @Before
    public void setup()
    {
        String address = "localhost";
        //Integer port = SERVER_PORT;
        //serverRootUrl = "http://" + address + ":" + port + "";

    }
/*
    @Test
    public void shouldGetValidHttpResponse() throws UnirestException {
        String address = "localhost";
        Integer port = SERVER_PORT;
        serverRootUrl = "http://" + address + ":" + port + "";

        HttpResponse<JsonNode> reply =
                Unirest.get(serverRootUrl+"/v2/room/(0,0,0)" ).asJson();
        assertThat(reply.getStatus(), is(HttpServletResponse.SC_OK));

        reply =
                Unirest.get(serverRootUrl+"/v2/room/(0,1,0)" ).asJson();
        assertThat(reply.getStatus(), is(HttpServletResponse.SC_OK));

        reply =
                Unirest.get(serverRootUrl+"/v2/room/(9,9,9)" ).asJson();
        assertThat(reply.getStatus(), is(HttpServletResponse.SC_NOT_FOUND));

    }
    @Test
    public void shouldPostValidDataAndCreatedResponse() {
        String address = "localhost";

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        Map<String, Object> fields = new HashMap<>();

        String body = "{\n" +
        "    \"description\":\"Hej\",\n" +
        "    \"creatorId\":\"42\"\n" +
        "}";

        HttpResponse<String> reply =
                Unirest.post("http://localhost:8080/v2/room/(0,0,0)" )
                        .headers(headers)

                        .body(body)
                        .asString();
        assertThat(reply.getStatus(), is(HttpServletResponse.SC_OK));


    }

 */
}
