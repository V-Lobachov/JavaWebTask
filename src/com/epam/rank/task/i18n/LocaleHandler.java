package com.epam.rank.task.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleHandler {
	private static HashMap<String, String> locales = new HashMap<String, String>();
	static {
		locales.put("UA", "ua");
		locales.put("EN", "");
	}

	public static ResourceBundle changeLocale(String locale) {
		if(locale == null){
			locale = "";
		}
		
		String language = locales.get(locale);
		Locale loc = new Locale.Builder().setLanguage(language)
				.setRegion(locale).build();
		ResourceBundle messages = ResourceBundle.getBundle("com.epam.rank.task.i18n.locale.i18n",loc, new UTF8Control());
				return messages;
			
	}

}
