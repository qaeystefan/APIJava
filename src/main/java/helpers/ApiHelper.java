package helpers;

import com.google.gson.Gson;
import controllers.Repository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigLoader;
import utils.LoggingUtils;
import utils.Routes;

public class ApiHelper {

    private static final Logger logger = LoggerFactory.getLogger(ApiHelper.class);

    private static String baseURI;
    private static String bearerToken;


    public static void setup() {
        baseURI = ConfigLoader.getProperty("baseURI");
        bearerToken = ConfigLoader.getProperty("bearerToken");
        RestAssured.baseURI = baseURI;
    }

    // CREATE
    public static Pair<Integer, Repository.RepositoryResponse> createRepository(String repositoryName) {

        logger.info("Creating repository with name: {}", repositoryName);
        Repository.RepositoryRequest repositoryRequest = new Repository.RepositoryRequest(repositoryName);

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .body(new Gson().toJson(repositoryRequest))
                .post(Routes.POST_REPO)
                .then().extract().response();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        if (statusCode != 201) {
            logger.error("Failed to create repository. Status code: {}", statusCode);
            LoggingUtils.logApiError(logger, responseBody);
        } else {
//            logger.info("Repository created successfully. Response: {}", responseBody);
            logger.info("Repository created successfully.");
        }

        Repository.RepositoryResponse repositoryResponse = new Gson().fromJson(responseBody, Repository.RepositoryResponse.class);
        return Pair.of(statusCode, repositoryResponse);
    }

    // GET
    public static Pair<Integer, String> getRepository(String repoName) {

        logger.info("Getting repository with name: {}", repoName);
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .get(Routes.GET_REPO + repoName)
                .then().extract().response();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        if (statusCode != 200) {
            logger.error("Failed to get repository. Status code: {}", statusCode);
            LoggingUtils.logApiError(logger, responseBody);
        } else {
            logger.info("Repository retrieved successfully.");
        }

        return Pair.of(statusCode, responseBody);
    }

    // UPDATE
    public static Pair<Integer, Repository.RepositoryResponse> updateRepository(String repoName, String updatedRepoName) {

        logger.info("Updating repository name: {} with name: {}", repoName, updatedRepoName);
        Repository.RepositoryRequest repositoryRequest = new Repository.RepositoryRequest(updatedRepoName);

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .body(new Gson().toJson(repositoryRequest))
                .patch(Routes.UPDATE_REPO + repoName)
                .then().extract().response();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        if (statusCode != 200) {
            logger.error("Failed to update repository. Status code: {}", statusCode);
            LoggingUtils.logApiError(logger, responseBody);
        } else {
            logger.info("Repository updated successfully.");
        }

        Repository.RepositoryResponse repositoryResponse = new Gson().fromJson(response.getBody().asString(), Repository.RepositoryResponse.class);
        return Pair.of(statusCode, repositoryResponse);
    }


    // DELETE
    public static Pair<Integer, String> deleteRepository(String repoName) {

        logger.info("Deleting repository with name: {}", repoName);
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + bearerToken)
                .delete(Routes.DELETE_REPOS + repoName)
                .then().extract().response();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        if (statusCode != 204) {
            logger.error("Failed to delete repository. Status code: {}", statusCode);
            LoggingUtils.logApiError(logger, responseBody);
        } else {
            logger.info("Repository deleted successfully.");
        }

        return Pair.of(statusCode, responseBody);
    }
}
