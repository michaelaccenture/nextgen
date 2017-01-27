package common;

public class NpsTrace {
	// Should have Log4j instead of trying to emulate it. 
	//
	// ALL		All levels including custom levels.
	// DEBUG	Designates fine-grained informational events that are most useful to debug an application.
	// INFO		Designates informational messages that highlight the progress of the application at coarse-grained level.
	// WARN		Designates potentially harmful situations.
	// ERROR	Designates error events that might still allow the application to continue running.
	// FATAL	Designates very severe error events that will presumably lead the application to abort.
	// OFF		The highest possible rank and is intended to turn off logging.
	// TRACE	Designates finer-grained informational events than the DEBUG.
	//
	// A log request of level p in a logger with level q is enabled if p >= q. 
	// This rule is at the heart of log4j. It assumes that levels are ordered. 
	// For the standard levels, we have ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF.
	
	public static Log logLevel = Log.ALL;
	public static int level = Log.ALL.getCode();
	private static String lastLogMessage = "";

	enum Log {
		OFF(0),
		ALL(1),
		DEBUG(2),
		INFO(3),
		WARN(4),
		ERROR(5),
		FATAL(6),
		TRACE(7);
		
		private final int code;
			
		Log(final int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	public static void setLevel(Log level) {
		NpsTrace.logLevel = level;
		NpsTrace.level = level.getCode();
	}
	    
	static String getLastLogMessage() {
		return lastLogMessage;
	}
	
	static void clearLastLogMessage() {
		lastLogMessage = "";
	}

	public static void trace(String description) {
		if (level >= Log.TRACE.getCode()) {
			lastLogMessage = String.format("%s:%s\n", Log.TRACE, description);
			System.out.println(lastLogMessage);
		}
	}

    public static void debug(String description) {
		if (level >= Log.DEBUG.getCode()) {
			lastLogMessage = String.format("%s:%s\n", Log.DEBUG, description);
			System.out.println(lastLogMessage);
		}
	}
	
	public static void info(String description) {
		if (level >= Log.INFO.getCode()) {
			lastLogMessage = String.format("%s:%s\n", Log.INFO, description);
			System.out.println(lastLogMessage);
		}
	}

	public static void warn(String description) {
		if (Log.WARN.getCode() <= level) {
			lastLogMessage = String.format("%s:%s\n", Log.WARN, description);
			System.out.println(lastLogMessage);
		}
	}
	
	public static void error(String description) {
		if (Log.ERROR.getCode() <= level) {
			lastLogMessage = String.format("%s:%s\n", Log.ERROR, description);
			System.out.println(lastLogMessage);
		}
	}

	public static void fatal(String description) {
		if (Log.FATAL.getCode() <= level) {
			lastLogMessage = String.format("%s:%s\n", Log.FATAL, description);
			System.out.println(lastLogMessage);
		}
	}
}
