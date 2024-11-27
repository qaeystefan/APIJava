package com.api.tests;

import com.api.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.utils.JsonLoader;
import com.api.utils.Routes;
import org.testng.annotations.BeforeClass;

//TESTING API
public class ApiGitHubTests {

    private String baseURI;
    private String bearerToken;

    @BeforeClass
    public void setup() {

        baseURI = ConfigLoader.getProperty("baseURI");
        bearerToken = ConfigLoader.getProperty("bearerToken");
        RestAssured.baseURI = baseURI;
    }


    @Test
    public void testPostRequest() {

        String requestBody = JsonLoader.loadJson("postBody.json");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .body(requestBody)
                .post(Routes.POST_ENDPOINT);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "Expected status code 201");
    }

    @Test
    public void testGetRequest() {

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .get(Routes.GET_ENDPOINT);

        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//        System.out.println("Response: " + responseBody);
//        System.out.println("Status Code: " + statusCode);

        Assert.assertEquals(statusCode, 200, "Expected status code 200");
//        Assert.assertTrue(responseBody.contains("repoApi"), "Response body should contain 'repoApi'");
    }

    @Test
    public void testUpdateRequest() {

        String updateBody = JsonLoader.loadJson("updateBody.json");

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .body(updateBody)
                .patch(Routes.UPDATE_ENDPOINT);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200");
    }

    @Test
    public void testDeleteRequest() {

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .delete(Routes.DELETE_ENDPOINT);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204, "Expected status code 204");
    }

}