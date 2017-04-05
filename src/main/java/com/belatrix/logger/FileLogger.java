package com.belatrix.logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger implements Log {
	
	private static FileLogger instance;
	private Map<String, String> config;
	private Logger logger;
	
	private FileLogger () {
		config = ConfigurationLoader.getConfigurationFrom("config.properties");
		logger = Logger.getLogger("MyLog");
	}
	
	public static FileLogger getLogger () {
		if (instance == null) {
			instance = new FileLogger();
		}
		return instance;
	}

	public void LogMessage(String message, LogLevel level) {
		String filePath = config.get("logpath") != null ? config.get("logpath") : "";
		String fileName = config.get("logfile") != null ? config.get("logfile") : "";
		String file = filePath + "/" + fileName;
		try {
			File logFile = new File(file);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			FileHandler fh = new FileHandler(file, true);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);
			logger.addHandler(fh);
			logger.log(level.getLoggerLevel(), message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
