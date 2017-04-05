package com.belatrix.logger;

import java.util.logging.Level;

public enum LogLevel {
	MESSAGE(1, Level.INFO), WARNING(2, Level.WARNING), ERROR(3, Level.SEVERE);
	private final int value;
	private final Level loggerLevel;
	
	private LogLevel (int value, Level loggerLevel) {
		this.value = value;
		this.loggerLevel = loggerLevel;
	}
	
	public int getValue () {
		return value;
	}
	
	public Level getLoggerLevel () {
		return loggerLevel;
	}
}
