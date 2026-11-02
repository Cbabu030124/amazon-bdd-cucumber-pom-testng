
package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    // Configure the base URL
    public static void setupApi() {

        RestAssured.baseURI =
                ConfigReader.getProperty("api.url");
    }

    // POST request - Create a new pet
    public static Response post(String path, Object body) {

        return RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(path);
    }

    // GET request - Retrieve pet details
    public static Response get(String path) {

        return RestAssured
                .given()
                .when()
                .get(path);
    }

    // PUT request - Update pet details
    public static Response put(String path, Object body) {

        return RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .put(path);
    }

    // DELETE request - Delete a pet
    public static Response delete(String path) {

        return RestAssured
                .given()
                .when()
                .delete(path);
    }
}
