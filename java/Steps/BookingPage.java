package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.ArrayList;

public class BookingPage {

    public static final String BASE_URL = "https://aluveapp-qa.co.za";

    public static Response response;

    @Given("The user has selects {string} and {string} date")
    public void the_user_has_selected_and_date(String check_in, String checkout) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.header("referer", "https://dev-aluvegh.co.za/").header("content-type", "application/json").get("/public/availablerooms/"+check_in+"/"+checkout+"");
    }

    @Then("the server returns availability")
    public void theServerReturnsAvailability() {
        RestAssured.baseURI = BASE_URL;
        Assert.assertEquals(200, response.getStatusCode());
        response.then().log().body();
    }

    @When("The selects book now")
    public void theSelectsBookNow() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.header("referer", "https://dev-aluvegh.co.za/").header("content-type", "application/json").body(ContentType.JSON).get("https://aluveapp-qa.co.za/public/reservations/create/[12]/Mluleki%20Mxolisi%20Nkosi/+27834889077/2/0/2022-09-15/2022-09-16/mlu78nkosi@gmail.com");
    }

    @Then("The server returns confirmation")
    public void theServerReturnsConfirmation() {
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        String resultMessage = new ArrayList<String>(JsonPath.from(jsonString).get("result_message")).get(0);
        Assert.assertEquals("Successfully created reservation", resultMessage);
        response.getContentType();
        response.then().log().body();
    }
}
