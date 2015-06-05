package com.epam.rank.task.model.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validation {
	protected ArrayList<String> errors;

	public Validation() {
		errors = new ArrayList<String>();
	}

	public boolean presence(Object user, String fieldName) {
		boolean result = true;
		try {
			Field field = user.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			if (field.get(user).equals("")) {
				result = false;
				errors.add(String.format("%s field must not be empty",
						fieldName));
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean isEmailValid(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
			errors.add(String.format("%s is invalid", email));
		}
		return result;
	}

	public void validateFormat(String format, String data, String message) {
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(data);

		if (!matcher.find()) {
			errors.add(message);
		}
	}

	public ArrayList<String> validationReport() {
		return errors;
	}
}
