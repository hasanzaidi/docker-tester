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
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

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
        assertThat(jsonResponse.size(), greaterThanOrEqualTo(2));

        assertThat(jsonResponse.get(0).get("emailAddress").asText(), is("user1@gmail.com"));
        assertThat(jsonResponse.get(0).get("password").asText(), is("abc123"));

        assertThat(jsonResponse.get(1).get("emailAddress").asText(), is("user2@yahoo.com"));
        assertThat(jsonResponse.get(1).get("password").asText(), is("letmein123"));
    }

    @Test
    public void createUsersReturns200() {
        // Given:
        String randomUserName = UUID.randomUUID().toString();
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"password\": \"mypassword\",\n" +
                        "    \"emailAddress\": \"" + randomUserName + "@fastmail.com\"\n" +
                        "}");

        // When:
        Response response = httpRequest.contentType(ContentType.JSON).post("/users");

        // Then:
        assertThat(response.getBody().asString(), response.getStatusCode(), is(200));
    }
}
