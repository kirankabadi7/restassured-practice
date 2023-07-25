package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequestBodys {

//	@Test(priority = 1)
	public void methodHashmap() {

		HashMap data = new HashMap();
		data.put("name", "kiran");
		data.put("location", "hubli");
		data.put("phone", "1234");
		String courceArr[] = { "C++", "Java" };
		data.put("courses", courceArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("kiran")).body("location", equalTo("hubli"))
				.body("phone", equalTo("1234")).body("courses[0]", equalTo("C++")).body("courses[1]", equalTo("Java"))
				.header("Content-Type", equalTo("application/json; charset=utf-8")).log().all();

	}

	@Test(priority = 2)
	public void deleteHashmapRecord() {
		given().contentType("application/json").when().delete("http://localhost:3000/students/4").then()
				.statusCode(200);
	}

//	Post using org.json

//	@Test(priority = 1)
	public void methodOrgJson() {

		JSONObject data = new JSONObject();
		data.put("name", "Vichal");
		data.put("location", "Harihar");
		data.put("phone", "5678");
		String courceArr[] = { "Mobile", "Camera" };
		data.put("courses", courceArr);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("Vichal")).body("location", equalTo("Harihar"))
				.body("phone", equalTo("5678")).body("courses[0]", equalTo("Mobile"))
				.body("courses[1]", equalTo("Camera"))
				.header("Content-Type", equalTo("application/json; charset=utf-8")).log().all();

	}

//	Post using POJ Class

//	@Test(priority = 1)
	public void methodPOJO() {
		Pojo_post data = new Pojo_post();
		data.setName("Virat");
		data.setLocation("Delhi");
		data.setPhone("123456");
		String courses[] = { "Cricket", "Insta" };
		data.setCourses(courses);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("Virat")).body("location", equalTo("Delhi"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("Cricket"))
				.body("courses[1]", equalTo("Insta")).header("Content-Type", equalTo("application/json; charset=utf-8"))
				.log().all();

	}

//	Post using json file

	@Test(priority = 1)
	public void methodJsonFile() throws FileNotFoundException {
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("Kiran K")).body("location", equalTo("Kannada"))
				.body("phone", equalTo("2392423")).body("courses[0]", equalTo("Selenium"))
				.body("courses[1]", equalTo("Java")).header("Content-Type", equalTo("application/json; charset=utf-8"))
				.log().all();

	}

}
