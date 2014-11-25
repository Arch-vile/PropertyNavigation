package com.moonillusions.propertynavigation;

import static com.moonillusions.propertynavigation.PropertyNavigation.of;
import static com.moonillusions.propertynavigation.PropertyNavigation.prop;
import static com.moonillusions.propertynavigation.PropertyNavigation.to;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class PropertyNavigationTest {

	@Test
	public void shallow_class() {
		assertThat(prop(of(Book.class).getAuthor()), equalTo("author"));
	}

	/**
	 * Note: will not work for dates in Java8.
	 * http://stackoverflow.com/questions
	 * /27092857/cglib-throws-an-illegalargumentexception
	 * -when-enhancing-the-java-util-date-class
	 */
	@Test
	public void test_for_date() {
		assertThat(prop(of(Book.class).getPublished()), equalTo("published"));
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
	public void to_includes_root_element() {
		assertThat(prop(to(Book.class).getAuthor().getBookCount()),
				equalTo("book.author.bookCount"));
	}

	@Test
	public void to_concrete_classes() {
		Book book = new Book();
		assertThat(prop(to(book).getAuthor().getBookCount()),
				equalTo("book.author.bookCount"));
	}

	@Test
	public void of_concrete_classes() {
		Book book = new Book();
		assertThat(prop(of(book).getAuthor().getBookCount()),
				equalTo("author.bookCount"));
	}

	@Test
	@Ignore
	public void getter_of_final_class() {
		assertThat(prop(of(Book.class).getAuthor().getName().getBytes()),
				equalTo("author.name.bytes"));
	}

}