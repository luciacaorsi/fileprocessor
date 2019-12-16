package en.arvato.fileprocessor.json;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import en.arvato.fileprocessor.model.Response;

public class JsonParserTest {
	
	private JsonParser jsonParser;
	
	@Before
	public void setUp() {
		jsonParser = JsonParser.getInstance();
	}
	
	@Test
	public void testGetJsonResponse() throws JsonProcessingException {
		
		String expectedJson = "{\r\n" + 
				"\"onlyInList1\": [\r\n" + 
				"\"Word1\",\r\n" + 
				"\"Word2\"\r\n" + 
				"],\r\n" + 
				"\"onlyInList2\": [\r\n" + 
				"\"Word3\",\r\n" + 
				"\"Word4\"\r\n" + 
				"],\r\n" + 
				"\"inBothLists\": [\r\n" + 
				"\"Word5\",\r\n" + 
				"\"Word6\"\r\n" + 
				"]\r\n" + 
				"}";
		
		Response response = new Response();
		response.setOnlyInList1(Arrays.asList("Word1", "Word2"));
		response.setOnlyInList2(Arrays.asList("Word3", "Word4"));
		response.setInBothLists(Arrays.asList("Word5", "Word6"));
		String jsonResponse = jsonParser.getJsonResponse(response);
		
	    ObjectMapper mapper = new ObjectMapper();
		assertEquals(mapper.readTree(expectedJson), mapper.readTree(jsonResponse));
	}
}