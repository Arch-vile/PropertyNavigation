package com.moonillusions.propertynavigation;

import java.util.Date;

public class Book {
	private Integer pageCount;
	public Author author;
	private int id;
	private Date published;
	
	public Book() {

	}

	public Integer getPageCount() {
		return this.pageCount;
	}

	public Author getAuthor() {
		return author;
	}
	
	public Date getPublished() {
		return published;
	}

	public int getId() {
		return id;
	}
}
