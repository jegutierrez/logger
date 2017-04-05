package com.belatrix.logger;

import java.util.logging.Logger;

public class ConsoleLogger implements Log {
	
	private static ConsoleLogger instance;
	private Logger logger;
	
	private ConsoleLogger () {
		logger = Logger.getLogger("MyLog");
	}
	
	public static ConsoleLogger getLogger () {
		if (instance == null) {
			instance = new ConsoleLogger();
		}
		return instance;
	}
	
	public void LogMessage(String message, LogLevel level) {
		logger.log(level.getLoggerLevel(), message);
	}
}
