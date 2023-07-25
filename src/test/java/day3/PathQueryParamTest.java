package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PathQueryParamTest {

//	https://reqres.in/api/users?page=2&id=5
	@Test(priority = 1)
	public void testQueryPathparams() {
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().statusCode(200).log().all();
	}

}
