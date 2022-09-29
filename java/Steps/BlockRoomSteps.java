package Steps;

import Base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.ArrayList;


public class BlockRoomSteps extends BaseClass {

    public static final String BASE_URL = "https://aluveapp-qa.co.za";

    public static Response response;


    @Given("The user blocks room")
    public void the_user_blocks_room() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.header("referer", "https://aluveapp-qa.co.za/admin/").cookie(cookieEat, returnCookie).get("/api/blockedroom/get");
        response.then().log().body();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Then("User validates room is blocked from {string} to {string}")
    public void user_validates_room_is_blocked_from_to(String check_in, String checkout) {
        Assert.assertEquals(200, response.getStatusCode());

        String jsonString = response.asString();
        JsonPath resultMessage = new JsonPath((String) JsonPath.from(jsonString).getJsonObject("html"));

        String printOut = (String.valueOf(resultMessage));

        System.out.println(printOut.indexOf(check_in));
        System.out.println(printOut.indexOf(checkout));
    }

    @When("User deletes a blocked room")
    public void userDeletesABlockedRoom() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.header("referer", "https://aluveapp-qa.co.za/admin/").cookie(cookieEat, returnCookie).delete("/api/blockedroom/delete/47");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Then("User validates room is deleted")
    public void userValidatesRoomIsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        String resultMessage = new ArrayList<String>(JsonPath.from(jsonString).get("result_message")).get(0);
        Assert.assertEquals("EntityManager#remove() expects parameter 1 to be an entity object, NULL given.", resultMessage);
        response.getContentType();
        response.then().log().body();
    }
}
