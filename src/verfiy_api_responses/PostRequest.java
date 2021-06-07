package verfiy_api_responses;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
	private static RequestSpecification requestSpec;

//creating request spec buliders and request, response logs globally
//request and response will store in a file called logging.txt	
	@BeforeClass
	public static void createRequestSpecification() throws FileNotFoundException {
		if (requestSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

		}

	}

//converting JSON into string and getting value of the key	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		System.out.println(resp);
		JsonPath js = new JsonPath(resp);
		System.out.println(js.get(key).toString());
		return js.get(key).toString();
	}

	@Test
	public void checkValidations() {
		// creating payload
		HashMap<String, Object> payload = new HashMap<>();
		payload.put("name", "Aravind");
		payload.put("job", "Lead Consultant");

		// passing payload to the request
		Response response = given().spec(requestSpec).body(payload).when().post("api/users");

		// checking status code is 201 or not
		assertEquals(response.getStatusCode(), 201);
		// checking content type json or not
		assertEquals(response.getContentType(), "application/json");

		String keyValue = "name";
		String expectedValue = "Aravind";
		// validating name filed from the generated response
		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

}
