package com.api.tests;

import controllers.Repository;
import helpers.ApiHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ContentUtils;

public class ApiGitHubTests {

    @BeforeClass
    public void setup() {
        ApiHelper.setup();
    }

    @Test(priority = 0)
    public void testPostRequest() {

        String repositoryName = "Testing";

        Pair<Integer, Repository.RepositoryResponse> result = ApiHelper.createRepository(repositoryName);
//        int statusCode = result.getLeft();
//        Repository.RepositoryResponse repositoryResponse = result.getRight();

        Assert.assertEquals(result.getLeft(), HttpStatus.SC_CREATED, "Expected status code 201");
        Assert.assertEquals(result.getRight().getName(), repositoryName, "Expected repository name should be: " + repositoryName);
    }

    @Test(priority = 1)
    public void testGetRequest() {

        String repositoryName = "Testing";

        Pair<Integer, String> result = ApiHelper.getRepository(repositoryName);
        String responseBody = result.getRight();

        Assert.assertEquals(result.getLeft(), HttpStatus.SC_OK, "Expected status code 200");
        Assert.assertTrue(responseBody.contains(repositoryName), "Response body should contain the repository name: " + repositoryName);
    }

    @Test(priority = 1)
    public void testPostRequestNegative() {

        String repositoryName = "Testing";

        Pair<Integer, Repository.RepositoryResponse> result = ApiHelper.createRepository(repositoryName);
        Assert.assertEquals(result.getLeft(), HttpStatus.SC_UNPROCESSABLE_ENTITY, "Expected status code 422");
    }

    @Test(priority = 2)
    public void testUpdateRequest() {
        String repoName = "Testing";
        String updatedRepoName = "updatedTesting";

        Pair<Integer, Repository.RepositoryResponse> result = ApiHelper.updateRepository(repoName, updatedRepoName);
        Repository.RepositoryResponse repositoryResponse = result.getRight();

        Assert.assertEquals(result.getLeft(), HttpStatus.SC_OK, "Expected status code 200");
        Assert.assertEquals(repositoryResponse.getName(), updatedRepoName, "Repository name should match the updated name: " + updatedRepoName);
    }

    @Test(priority = 3)
    public void testDeleteRequest() {
        String repoName = "Testing";

        Pair<Integer, String> result = ApiHelper.deleteRepository(repoName);
        String responseBody = result.getRight();

        Assert.assertEquals(result.getLeft(), HttpStatus.SC_NO_CONTENT, "Expected status code 204");
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty after deletion");
    }

    @Test(priority = 4)
    public void testGetRequestREADME() {

        String repositoryName = "Pera";
        String content = "POSTAR";
        String key = "content";

        Pair<Integer, String> result = ApiHelper.getRepositoryReadme(repositoryName);
        String responseBody = result.getRight();

        String sanitizedContent = ContentUtils.extractAndSanitizeContent(responseBody, key);
        String decodedContent = ContentUtils.decodeBase64Content(sanitizedContent);

        Assert.assertEquals(result.getLeft(), HttpStatus.SC_OK, "Expected status code 200");
        Assert.assertEquals(decodedContent, content, "Decoded content should match the expected content:" + content);
    }

}