package Steps;

import Base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class ReservationSteps extends BaseClass {

    private BaseClass base;
    public ReservationSteps(BaseClass base) {
        this.base = base;
    }

    public static final String BASE_URL = "http://aluveapp-qa.co.za/";
    public static Response response;

    @When("User retrieves pending reservation with number {string}")
    public void user_retrieves_pending_reservation_with_number(String reservationNumber) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.redirects().follow(false).get("/api/reservation_html/" + reservationNumber + "").thenReturn().cookie(returnCookie);

    }


    @And("User updates guest ID {string}")
    public void userUpdatesGuestIDGuestID(String guestID) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.cookie("PHPSESSID",returnCookie).put("/api/guest/69/idnumber/" + guestID + "");
        response.then().log().body();
        response.getBody();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(returnCookie);

    }


    @Then("User validates ID successfully updated")
    public void userValidatesIDSuccessfullyUpdated() {
        RequestSpecification request = RestAssured.given();
        response = request.cookie("PHPSESSID",returnCookie).get("/api/guest/69/idnumber/142");
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(returnCookie);
        response.then().log().body();
        response.getBody();
    }
}
