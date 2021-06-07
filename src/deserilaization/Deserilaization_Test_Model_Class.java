package deserilaization;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import deserilaization_pojo.Data;
import deserilaization_pojo.GetUserDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class Deserilaization_Test_Model_Class {

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

	// Validating the response by deserilazing it from model class
	@Test
	public void deseriliazeResponseValidateFileds() {
		//stroing geneared response into the "GetUserDetials.class"
		GetUserDetails user = given().spec(requestSpec).expect().defaultParser(Parser.JSON).when().get("api/unknown")
				.as(GetUserDetails.class);

		System.out.println("From the generated response printing the values as below....... ");
		System.out.println("Totla results displayed per page is :" + user.getPer_page());
		System.out.println("Support URL is :" + user.getSupport().getUrl());
		System.out.println("Third User Name is :" + user.getData().get(3).getName());

		System.out.println("*****************************************************************");
		System.out.println("Validating the response..........");

		String[] expected_pantonee_values = { "15-4020", "17-2031", "19-1664", "14-4811", "17-1456", "15-5217" };
		// getting user detils into the list
		List<Data> userdetails = user.getData();
		// Get the pantone values of all users
		ArrayList<String> actual_pantone_values = new ArrayList<String>();
		for (int i = 0; i < userdetails.size(); i++) {
			actual_pantone_values.add(userdetails.get(i).getPantone_value());

		}
		System.out.println("*****************************************************************");
		System.out.println("Validating the pantone values of all users ..........");
		List<String> expected_pantone_values = Arrays.asList(expected_pantonee_values);
		// validating all the pantone values of all users
		Assert.assertTrue(actual_pantone_values.equals(expected_pantone_values));
		System.out.println("Acutal pantone values :" + actual_pantone_values);

		// validating name of the third user if pantone value is matched
		System.out.println("*****************************************************************");
		System.out.println("Validating the third user name based on pantone value ..........");
		String third_pantone_value = "14-4811";
		if (user.getData().get(3).getPantone_value().equals(third_pantone_value)) {
			String expected_thrid_user_name = "aqua sky";
			String actual_third_user_name = user.getData().get(3).getName();
			Assert.assertTrue(actual_third_user_name.equals(expected_thrid_user_name));
			System.out.println("Third User Name is :" + user.getData().get(3).getName());

		}
		System.out.println("*****************************************************************");
		System.out.println("Validating total results per page ..........");
		String expected_results_for_page = "12";
		String actual_results_for_page = user.getTotal();
		Assert.assertTrue(actual_results_for_page.equals(expected_results_for_page));
		System.out.println("Totla results displayed per page is :" + actual_results_for_page);

		System.out.println("*****************************************************************");
		System.out.println("Validating the support url ..........");
		String expected_support_url = "https://reqres.in/#support-heading";
		String actual_support_url = user.getSupport().getUrl();
		Assert.assertTrue(actual_support_url.equals(expected_support_url));
		System.out.println("Support URL is :" + actual_support_url);
	}

}
