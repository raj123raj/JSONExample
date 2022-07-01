package com.gfg.model;

import java.util.Arrays;

public class GFGWriter {

	private int id;
	private String writerName;
	private boolean status;
	private Address address;
	private long[] phoneNumbers;
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long[] getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(long[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***** GFG Portal Writer Details *****\n");
		sb.append("ID="+getId()+"\n");
		sb.append("Writer Name="+getWriterName()+"\n");
		sb.append("Status="+getStatus()+"\n");
		sb.append("Role="+getRole()+"\n");
		sb.append("Phone Numbers="+Arrays.toString(getPhoneNumbers())+"\n");
		sb.append("Address="+getAddress());
		sb.append("\n*****************************");
		
		return sb.toString();
	}
}
