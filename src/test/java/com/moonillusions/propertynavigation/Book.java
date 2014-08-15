package com.moonillusions.propertynavigation;

public class Book {
	private Integer pageCount;
	public Author author;
	private int id;

	public Book() {

	}

	public Integer getPageCount() {
		return this.pageCount;
	}

	public Author getAuthor() {
		return author;
	}

	public int getId() {
		return id;
	}
}
