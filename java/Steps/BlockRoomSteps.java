package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class BlockRoomSteps {
    public static final String BASE_URL = "https://aluveapp-qa.co.za";
    public static Response response;
    public static String sessionID = "9dc97f9a90cc8f2112d9b58f68af41d0";

    @Given("The user has signed in")
    public void theUserHasSignedIn() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.param("_username", "info%40aluvegh.co.za");
        request.param("_password", "3782");
        request.header("referer", "https://aluveapp-qa.co.za/login");
        request.header("Accept", ContentType.JSON.getAcceptHeader());
        response = request.cookie("PHPSESSID", sessionID).when().post("/login");
        response.then().log().body();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

    }

    @Given("The user blocks room")
    public void the_user_blocks_room() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.header("referer", "https://aluveapp-qa.co.za/admin/").cookie("PHPSESSID", sessionID).get("/api/blockedroom/get");
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
}
