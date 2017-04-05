package com.belatrix.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationLoader {
	
	public static Map<String, String> getConfigurationFrom(String filename) {
		Properties prop = new Properties();
		InputStream input = null;
		Map<String, String> config = new HashMap<String, String>();
		try {
			input = new FileInputStream(filename);
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				config.put(key, value);
			}
		} catch (IOException ex) {
			throw new IllegalStateException("Error while reading the 'config.properties' file");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return config;
	}
}
