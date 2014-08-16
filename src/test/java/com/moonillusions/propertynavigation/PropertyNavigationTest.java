package com.moonillusions.propertynavigation;

import static com.moonillusions.propertynavigation.PropertyNavigation.of;
import static com.moonillusions.propertynavigation.PropertyNavigation.prop;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class PropertyNavigationTest {

	@Test
	public void shallow_class() {
		assertThat(prop(of(Book.class).getAuthor()), equalTo("author"));
	}

	@Test
	public void deep_class() {
		assertThat(prop(of(Book.class).getAuthor().getAddress()),
				equalTo("author.address"));
	}

	@Test
	public void shallow_final_class() {
		assertThat(prop(of(Book.class).getPageCount()), equalTo("pageCount"));
	}

	@Test
	public void deep_final_class() {
		assertThat(prop(of(Book.class).getAuthor().getName()),
				equalTo("author.name"));
	}

	@Test
	public void shallow_primitive() {
		assertThat(prop(of(Book.class).getId()), equalTo("id"));
	}

	@Test
	public void deep_primitive() {
		assertThat(prop(of(Book.class).getAuthor().getBookCount()),
				equalTo("author.bookCount"));
	}

	@Test
	@Ignore
	public void getter_of_final_class() {
		assertThat(prop(of(Book.class).getAuthor().getName().getBytes()),
				equalTo("author.name.bytes"));
	}

}