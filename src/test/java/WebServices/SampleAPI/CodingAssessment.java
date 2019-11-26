package WebServices.SampleAPI;

import static com.jayway.restassured.RestAssured.*;
import java.io.IOException;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.path.json.JsonPath;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Set;

public class CodingAssessment {
	
	@Test(enabled=false)
	public void getJSONPlaceHolder() {
		given()
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();
	}

	@Test(enabled=true)
	public void checkResponse() throws IOException {
		
		String s = given()
				.when()
				.get("https://jsonplaceholder.typicode.com/posts")
				.then()
				.extract().response().toString();
		
		ObjectMapper objmapper = new ObjectMapper();
		ObjectNode objnode = objmapper.createObjectNode();
		JsonNode jnode = objmapper.readTree(s);
		JsonPath jpath = new JsonPath(s);
		
		Map<String,String> map = jpath.getMap("$");
		Set<String> set = map.keySet();
		
		for(String str:set){
			objnode.set(str, jnode.get(str).get("userId"));
			if(jnode.get(str).get("userId").asText().contains("7")) 
			{
				System.out.println("Name of the titles with userID:7 is ");
				System.out.println(jnode.get(str).get("title"));
			}
		}
	
	}
}
