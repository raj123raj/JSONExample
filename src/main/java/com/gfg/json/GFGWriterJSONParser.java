package com.gfg.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.gfg.model.Address;
import com.gfg.model.GFGWriter;

public class GFGWriterJSONParser {

	public static final String FILE_NAME = "gfgwriterfile.txt";

	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);

		JsonParser jsonParser = Json.createParser(fis);

		/**
		 * We can create JsonParser from JsonParserFactory also with below code
		 * JsonParserFactory factory = Json.createParserFactory(null);
		 * jsonParser = factory.createParser(fis);
		 */

		GFGWriter gfgWriter = new GFGWriter();
		Address address = new Address();
		String keyName = null;
		List<Long> phoneNums = new ArrayList<Long>();
		
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				keyName = jsonParser.getString();
				break;
			case VALUE_STRING:
				setStringValues(gfgWriter, address, keyName, jsonParser.getString());
				break;
			case VALUE_NUMBER:
				setNumberValues(gfgWriter, address, keyName, jsonParser.getLong(), phoneNums);
				break;
			case VALUE_FALSE:
				setBooleanValues(gfgWriter, address, keyName, false);
				break;
			case VALUE_TRUE:
				setBooleanValues(gfgWriter, address, keyName, true);
				break;
			case VALUE_NULL:
				// don't set anything
				break;
			default:
				// we are not looking for other events
			}
		}
		gfgWriter.setAddress(address);
		long[] nums = new long[phoneNums.size()];
		int index = 0;
		for(Long l :phoneNums){
			nums[index++] = l;
		}
		gfgWriter.setPhoneNumbers(nums);
		
		System.out.println(gfgWriter);
		
		//close resources
		fis.close();
		jsonParser.close();
	}

	private static void setNumberValues(GFGWriter gfgWriter, Address address,
			String keyName, long value, List<Long> phoneNumbers) {
		switch(keyName){
		case "zipcode":
			address.setZipcode((int)value);
			break;
		case "id":
			gfgWriter.setId((int) value);
			break;
		case "phoneNumbers":
			phoneNumbers.add(value);
			break;
		default:
			System.out.println("Unknown element with key="+keyName);	
		}
	}

	private static void setBooleanValues(GFGWriter gfgWriter, Address address,
			String key, boolean value) {
		if("status".equals(key)){
			gfgWriter.setStatus(value);
		}else{
			System.out.println("Unknown element with key="+key);
		}
	}

	private static void setStringValues(GFGWriter gfgWriter, Address address,
			String key, String value) {
		switch(key){
		case "writerName":
			gfgWriter.setWriterName(value);
			break;
		case "role":
			gfgWriter.setRole(value);
			break;
		case "city":
			address.setCity(value);
			break;
		case "street":
			address.setStreet(value);
			break;
		default:
			System.out.println("Unkonwn Key="+key);
				
		}
	}

}
