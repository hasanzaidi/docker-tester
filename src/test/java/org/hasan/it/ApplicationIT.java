package org.hasan.it;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplicationIT {
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void getStatusReturns200() {
        // Given:
        RequestSpecification httpRequest = RestAssured.given();

        // When:
        Response response = httpRequest.contentType(ContentType.JSON).get("/status");

        // Then:
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().asString(), is("OK"));
    }

    @Test
    public void getUsersReturns200() throws IOException {
        // Given:
        RequestSpecification httpRequest = RestAssured.given();

        // When:
        Response response = httpRequest.contentType(ContentType.JSON).get("/users");

        // Then:
        assertThat(response.getBody().asString(), response.getStatusCode(), is(200));

        JsonNode jsonResponse = mapper.readTree(response.getBody().asString());
        assertThat(jsonResponse.size(), is(2));

        assertThat(jsonResponse.get(0).get("username").asText(), is("user1"));
        assertThat(jsonResponse.get(0).get("password").asText(), is("abc123"));
        assertThat(jsonResponse.get(0).get("emailAddress").asText(), is("user1@gmail.com"));

        assertThat(jsonResponse.get(1).get("username").asText(), is("user2"));
        assertThat(jsonResponse.get(1).get("password").asText(), is("letmein123"));
        assertThat(jsonResponse.get(1).get("emailAddress").asText(), is("user2@yahoo.com"));
    }
}
