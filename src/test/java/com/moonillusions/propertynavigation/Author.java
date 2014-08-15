package com.moonillusions.propertynavigation;

public class Author {
	private String name;
	public Integer age;
	private Address address;

	public String getName() {
		return this.name;
	}

	public int getBookCount() {
		return 1;
	}

	public Address getAddress() {
		return this.address;
	}
}
