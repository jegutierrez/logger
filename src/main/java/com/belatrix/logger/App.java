package com.belatrix.logger;

public class App {
    public static void main( String[] args ) {
        JobLogger.LogMessage("Mensaje de log", LogLevel.ERROR, LogType.CONSOLE);
		JobLogger.LogMessage("Mensaje de log", LogLevel.WARNING, LogType.FILE);
		JobLogger.LogMessage("Mensaje de log", LogLevel.MESSAGE, LogType.DATABASE);
    }
}
