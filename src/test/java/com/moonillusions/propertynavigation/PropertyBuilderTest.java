package com.moonillusions.propertynavigation;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Test;

public class PropertyBuilderTest {

	@Test
	public void sss() throws SecurityException, NoSuchMethodException {

		Method method = Book.class.getMethod("getAuthor", null);

		PropertyBuilder builder = new PropertyBuilder();
		builder.setRoot(Book.class);
		builder.append(method);
		assertThat(builder.toProperty(), equalTo("book.author"));

	}

}
