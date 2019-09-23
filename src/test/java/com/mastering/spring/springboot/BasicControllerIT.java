package com.mastering.spring.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIT {
    private static final String LOCAL_HOST = "http://localhost:";

    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void welcome() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createURL("/welcome"), String.class);
        assertThat(response.getBody(), equalTo("Hello World"));

    }

    @Test
    public void welcomeWithObject() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createURL("/welcome-with-object"), String.class);
        assertThat(response.getBody(), containsString("Hello World"));
    }



    private String createURL(String uri) {
        return LOCAL_HOST + port + uri;
    }

}
