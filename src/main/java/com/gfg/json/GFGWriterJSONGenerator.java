package com.gfg.json;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import com.gfg.model.GFGWriter;

public class GFGWriterJSONGenerator {

	public static void main(String[] args) throws IOException {
		OutputStream fos = new FileOutputStream("gfgwriter_stream.txt");
		JsonGenerator jsonGenerator = Json.createGenerator(fos);
		/**
		 * We can get JsonGenerator from Factory class also
		 * JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
		 * jsonGenerator = factory.createGenerator(fos);
		 */
		
		GFGWriter gfgWriter = GFGWriterCreation.createGFGWriter();
		jsonGenerator.writeStartObject(); // {
		jsonGenerator.write("id", gfgWriter.getId()); // "id":100
		jsonGenerator.write("writerName", gfgWriter.getWriterName());
		jsonGenerator.write("role", gfgWriter.getRole());
		jsonGenerator.write("status", gfgWriter.getStatus());
		
		jsonGenerator.writeStartObject("address") //start of address object
			.write("street", gfgWriter.getAddress().getStreet())
			.write("city",gfgWriter.getAddress().getCity())
			.write("zipcode",gfgWriter.getAddress().getZipcode())
			.writeEnd(); //end of address object
		
		jsonGenerator.writeStartArray("phoneNumbers"); //start of phone num array
		for(long num : gfgWriter.getPhoneNumbers()){
			jsonGenerator.write(num);
		}
		jsonGenerator.writeEnd(); // end of phone num array
		jsonGenerator.writeEnd(); // }
		
		jsonGenerator.close();
		
	}

}
