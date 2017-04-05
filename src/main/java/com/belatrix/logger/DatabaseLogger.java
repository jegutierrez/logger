package com.belatrix.logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;


public class DatabaseLogger implements Log {
	
	private static DatabaseLogger instance;
	private Properties connectionProps;
	private Map<String, String> config;
	
	private DatabaseLogger () {
		config = ConfigurationLoader.getConfigurationFrom("config.properties");
		connectionProps = new Properties();
		connectionProps.put("user", config.get("username"));
		String pass = config.get("password") != null ? config.get("password") : "" ;
		connectionProps.put("password", pass);
	}
	
	public static DatabaseLogger getLogger () {
		if (instance == null) {
			instance = new DatabaseLogger();
		}
		return instance;
	}

	public void LogMessage(String message, LogLevel level) {
		try {
			if (config.get("dbms") == null || config.get("servername") == null || config.get("portnumber") == null ||
				config.get("dbname") == null || config.get("username") == null) {
				String msg = "dbms, servername, portnumber, dbname and username properties must be configured";
				throw new IllegalStateException(msg);
			}
			Connection conn = DriverManager.getConnection("jdbc:" + config.get("dbms") + "://" + config.get("servername")
					+ ":" + config.get("portnumber") + "/" + config.get("dbname"), connectionProps);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Log VALUES(?, ?)");
			message = level.name() + ": " + message;
			stmt.setString(1, message);
			stmt.setInt(2, level.getValue());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
