package com.moonillusions.propertynavigation;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class PropertyBuilderTest {

	Method getter1;
	Method getter2;

	PropertyBuilder builder;

	@Before
	public void init() throws SecurityException, NoSuchMethodException {
		builder = new PropertyBuilder();
		builder.setRoot(Book.class);
		getter1 = Book.class.getMethod("getAuthor", null);
		getter2 = Book.class.getMethod("getPageCount", null);
	}

	@Test
	public void single_getter_to_property() {
		builder.append(getter1);
		assertThat(builder.toProperty(), equalTo("author"));
	}

	@Test
	public void chained_getters_to_property() {
		builder.append(getter1);
		builder.append(getter2);
		assertThat(builder.toProperty(), equalTo("author.pageCount"));
	}

}
