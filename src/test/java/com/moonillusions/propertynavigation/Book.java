package com.moonillusions.propertynavigation;


public class Book {
	public String title;
	private Integer pageCount;
	public Author author;
	public Author coAuthor;

	public Book() {

	}

	public Integer getPageCount() {
		return this.pageCount;
	}
	
	public Author getCoAuthor() {
		return coAuthor;
	}
}
