package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test()
	public void cookietests() {

//		given().when().get("https://www.google.com").then();

		Response res = given().when().get("https://www.google.com");
//		Single cookie
		String cookie = res.getCookie("ACE");
		System.out.println("Value of cookie is >> " + cookie);
// this gives all the keys
		Map<String, String> cookies = res.getCookies();

		for (String k : cookies.keySet()) {
			String cookie_value = res.getCookie(k);
			System.out.println("COOKIE NAME IS >> " + k + " Value of cookie is " + cookie_value);
		}

	}

}
