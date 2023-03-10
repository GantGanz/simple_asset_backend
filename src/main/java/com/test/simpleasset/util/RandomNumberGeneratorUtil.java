package com.test.simpleasset.util;

import java.util.Random;

public class RandomNumberGeneratorUtil {
	public static String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
		final int leftLimit = 48; // numeral '0'
		final int rightLimit = 122; // letter 'z'
		final int targetStringLength = 5;
	    final Random random = new Random();

	    final String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString.toUpperCase();
	}
}
