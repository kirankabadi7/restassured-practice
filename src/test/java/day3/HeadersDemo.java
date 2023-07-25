package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

//	@Test()
	public void headerTests() {

		given().when().get("https://www.google.com").then().header("Content-Encoding", "gzip").header("Server", "gws");
//		
	}

	@Test()
	public void headersTests() {

		Response res = given().when().get("https://www.google.com");
		String header = res.getHeader("Server");
//		getting single header value
		System.out.println("Header valu is >> " + header);

		Headers headers = res.getHeaders();

		for (Header hd : headers) {
			System.out.println(hd.getName());
			System.out.println(hd.getValue());
		}

//		
	}

}
