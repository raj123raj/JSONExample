package com.gfg.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.gfg.model.Address;
import com.gfg.model.GFGWriter;

public class GFGWriterJSONReader {

	public static final String JSON_FILE="gfgwriter.txt";
	
	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream(JSON_FILE);
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);
		
		/**
		 * We can create JsonReader from Factory also
		JsonReaderFactory factory = Json.createReaderFactory(null);
		jsonReader = factory.createReader(fis);
		*/
		
		//get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();
		
		//we can close IO resource and JsonReader now
		jsonReader.close();
		fis.close();
		
		//Retrieve data from JsonObject and create Employee bean
		GFGWriter gfgWriter = new GFGWriter();
		
		gfgWriter.setId(jsonObject.getInt("id"));
		gfgWriter.setWriterName(jsonObject.getString("writerName"));
		gfgWriter.setStatus(jsonObject.getBoolean("status"));
		gfgWriter.setRole(jsonObject.getString("role"));
		
		//reading arrays from json
		JsonArray jsonArray = jsonObject.getJsonArray("phoneNumbers");
		long[] numbers = new long[jsonArray.size()];
		int index = 0;
		for(JsonValue value : jsonArray){
			numbers[index++] = Long.parseLong(value.toString());
		}
		gfgWriter.setPhoneNumbers(numbers);
		
		//reading inner object from json object
		JsonObject innerJsonObject = jsonObject.getJsonObject("address");
		Address address = new Address();
		address.setStreet(innerJsonObject.getString("street"));
		address.setCity(innerJsonObject.getString("city"));
		address.setZipcode(innerJsonObject.getInt("zipcode"));
		gfgWriter.setAddress(address);
		
		//print GFGWriter bean information
		System.out.println(gfgWriter);
		
	}

}
