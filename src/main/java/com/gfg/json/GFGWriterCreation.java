package com.gfg.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import com.gfg.model.Address;
import com.gfg.model.GFGWriter;

public class GFGWriterCreation {

	public static void main(String[] args) throws FileNotFoundException {

		GFGWriter gfgWriter = createGFGWriter();

		JsonObjectBuilder writerBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneNumberBuilder = Json.createArrayBuilder();

		for (long phone : gfgWriter.getPhoneNumbers()) {
			phoneNumberBuilder.add(phone);
		}
		
		addressBuilder.add("street", gfgWriter.getAddress().getStreet())
						.add("city", gfgWriter.getAddress().getCity())
							.add("zipcode", gfgWriter.getAddress().getZipcode());
		
		writerBuilder.add("id", gfgWriter.getId())
					.add("writerName", gfgWriter.getWriterName())
						.add("status", gfgWriter.getStatus())
							.add("role", gfgWriter.getRole());
		
		writerBuilder.add("phoneNumbers", phoneNumberBuilder);
		writerBuilder.add("address", addressBuilder);
		
		JsonObject gfgWriterJsonObject = writerBuilder.build();
		
		System.out.println("GFGWriter JSON String\n"+gfgWriterJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("gfgwriter.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(gfgWriterJsonObject);
		jsonWriter.close();
	}
	

	public static GFGWriter createGFGWriter() {

		GFGWriter gfgWriter = new GFGWriter();
		gfgWriter.setId(100);
		gfgWriter.setWriterName("GeekA");
		gfgWriter.setStatus(false);
		gfgWriter.setPhoneNumbers(new long[] { 123456, 987654 });
		gfgWriter.setRole("Beginner");

		Address add = new Address();
		add.setCity("Bangalore");
		add.setStreet("MG Road");
		add.setZipcode(560100);
		gfgWriter.setAddress(add);

		return gfgWriter;
	}

}
