package dk.msdo.controller;


import dk.msdo.caveservice.CaveServiceApplication;
import dk.msdo.caveservice.Configuration;
import dk.msdo.caveservice.controller.RoomController;

import dk.msdo.caveservice.repositories.MemoryRoomRepositoryImpl;
import dk.msdo.repository.TestContainerRedisDB;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomController.class, properties = "storage.room=memoryStorage" )
@ContextConfiguration(classes={CaveServiceApplication.class, MemoryRoomRepositoryImpl.class})
public class RoomControllerTest {

/*

//  Requires CaveService to be running..... how to launch as a part of the test?
    @Test
    public void getRoom() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8080/room/(0,0,0)";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("employeeList"));
    }

 */
}

