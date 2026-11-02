
package steps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.ApiUtils;

public class PetstoreApiSteps {

    private int petId;

    private final String name = "AutomationPet";
    private final String updated = "UpdatedAutomationPet";

    private Map<String, Object> pet;
    private Response response;

    // Prepare unique test data
    @Given("I prepare unique pet test data")
    public void prepare() {

        petId = ThreadLocalRandom.current()
                .nextInt(100000, 2000000000);

        pet = new HashMap<>();

        pet.put("id", petId);
        pet.put("name", name);
        pet.put("status", "available");
    }

    // Create a pet using POST request
    @When("I create a pet using POST request")
    public void create() {

        response = ApiUtils.post("/pet", pet);

        System.out.println(response.asString());
    }

    // Verify POST response
    @Then("I should verify the pet creation response")
    public void verifyCreate() {

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
    }

    // Retrieve pet using GET request
    @When("I retrieve the pet using GET request")
    public void read() {

        response = ApiUtils.get("/pet/" + petId);
    }

    // Verify GET response
    @Then("I should verify the retrieved pet details")
    public void verifyRead() {

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
    }

    // Update pet using PUT request
    @When("I update the pet using PUT request")
    public void update() {

        pet.put("name", updated);
        pet.put("status", "sold");

        response = ApiUtils.put("/pet", pet);
    }

    // Verify PUT response
    @Then("I should verify the updated pet details")
    public void verifyUpdate() {

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId);
        Assert.assertEquals(response.jsonPath().getString("name"), updated);
        Assert.assertEquals(response.jsonPath().getString("status"), "sold");
    }

    // Delete pet using DELETE request
    @When("I delete the pet using DELETE request")
    public void delete() {

        response = ApiUtils.delete("/pet/" + petId);
    }

    // Verify DELETE response and confirm pet is unavailable
    @Then("I should verify the pet is no longer available")
    public void verifyDelete() {

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "DELETE failed"
        );

        Response check = ApiUtils.get("/pet/" + petId);

        String body = check.asString().toLowerCase();

        boolean missing =
                check.getStatusCode() == 404
                || check.getStatusCode() == 400
                || (check.getStatusCode() == 200
                    && (body.contains("not found") || body.contains("error")));

        Assert.assertTrue(
                missing,
                "Pet still appears retrievable: " + body
        );
    }
}
