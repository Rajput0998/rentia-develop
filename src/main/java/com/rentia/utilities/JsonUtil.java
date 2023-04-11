package com.rentia.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil 
{
   public static JsonNode stringToJson(String str) throws JsonMappingException, JsonProcessingException 
   {
		
		ObjectMapper obj = new ObjectMapper();
		
		return obj.readTree(str);
	}

}