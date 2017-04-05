package com.belatrix.logger;

import java.text.DateFormat;
import java.util.Date;

public class JobLogger {
	
	public static void LogMessage(String message, LogLevel level, LogType type) {
		switch (type) {
			case FILE:
				FileLogger.getLogger().LogMessage(formatMessage(message), level);
				break;
			case DATABASE:
				DatabaseLogger.getLogger().LogMessage(formatMessage(message), level);
				break;
			case CONSOLE:
				ConsoleLogger.getLogger().LogMessage(formatMessage(message), level);
				break;
		}
	}
	
	public static String formatMessage (String message) {
		message = message.trim();
		if (message == null || message.length() == 0) {
			throw new IllegalArgumentException("A log message must be specified");
		}
		message = DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + message;
		return message;
	}
}
