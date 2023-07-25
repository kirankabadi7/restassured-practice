package day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpsRequest {

	/*
	 * given() content type, set cookies, add auth, add param, set headers info
	 * etc....
	 * 
	 * when() get, post, put, delete
	 * 
	 * then() validate status code, extract response, extract headers cookies &
	 * response body....
	 * 
	 */
	int id;

	@Test(priority = 1)
	public void getUser() {

		given().contentType("application/json")

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2, dependsOnMethods = { "getUser" })
	public void createUser() {

		HashMap string = new HashMap();
		string.put("name", "morpheus");
		string.put("job", "leader");

		id = given().contentType("application/json").body(string)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

//				.then().statusCode(201).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "getUser", "createUser" })
	public void updateUser() {
		HashMap string = new HashMap();
		string.put("name", "Kiran");
		string.put("job", "Teacher");

		given().contentType("application/json").body(string)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}

	@Test(priority = 4, dependsOnMethods = { "getUser", "createUser", "updateUser" })
	public void deleteUser() {

		given().contentType("application/json")

				.when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204).log().all();

	}

}
