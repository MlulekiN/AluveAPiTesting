package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SendMessageSteps {
    public static Response response;

    @And("User sends a message")
    public void userSendsAMessage() {
        baseURI = "https://aluveapp-qa.co.za/public/property/contact/Mluleki/mlu78nkosi@gmail.com/+27834889077/Yo%20yo%20yo?callback=jQuery111108150594468083903_1661862160030&_=1661862160031";
        response = given().request().header("referer", "https://dev-aluvegh.co.za/").when().get();
        response.then().log().all();
    }

    @Then("the server returns a success message")
    public void serverReturnsASuccessMessage() {
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        String resultMessage = new ArrayList<String>(JsonPath.from(jsonString).get("result_message")).get(0);
        Assert.assertEquals("Successfully sent message. Thank you", resultMessage);
    }
}
