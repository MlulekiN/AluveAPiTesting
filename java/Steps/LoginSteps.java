package Steps;

import Base.BaseClass;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class LoginSteps extends BaseClass {
    private BaseClass base;

    public LoginSteps(BaseClass base) {
        this.base = base;
    }

    public static final String BASE_URL = "http://aluveapp-qa.co.za";
    public static Response response;

    @Given("The user has signed in")
    public static void theUserHasSignedIn() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = given();
        request.param("_username", "info@aluvegh.co.za");
        request.param("_password", "3782");
        response = request.redirects().follow(false).header("referer", "https://aluveapp-qa.co.za/").cookie(cookieEat).when().post("/login");
        returnCookie = response.cookie(cookieEat);
        response.then().log().body();
        response.getStatusCode();
        System.out.println("PHPSESSID: " + returnCookie);
    }
}
