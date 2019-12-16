package en.arvato.fileprocessor.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import en.arvato.fileprocessor.model.Response;

/**
 * Helper class that allows to convert an object into a JSON representation.
 * 
 * @author Lucía Caorsi
 */
public class JsonParser {

    private static JsonParser INSTANCE;
    
    private JsonParser() {        
    }
     
    public static JsonParser getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new JsonParser();
        }
        return INSTANCE;
    }
    
	/**
	 * Parses a {@link Response} into a JSON representation.
	 * 
	 * @param response
	 *            the object to be parsed
	 * @return a String containing the JSON value
	 * @throws JsonProcessingException
	 *             if the object cannot be parsed
	 */
	public String getJsonResponse(Response response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(response);
	}
}