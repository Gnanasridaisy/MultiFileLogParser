public class LogEntry {
	// Using String datatypes to store each log field
    private final String timestamp;
    private final String level;
    private final String module;
    private final String message;
    private final String user;
    private final String sourceFile;
    
// Parameterized Constructor used to initialize the log details
    public LogEntry(String timestamp, String level, String module, String message, String user, String sourceFile) {
        this.timestamp = timestamp;
        this.level = level;
        this.module = module;
        this.message = message;
        this.user = user;
        this.sourceFile = sourceFile;
    }
    
// Getter Methods- used to access private data members (Encapsulation)
    public String getTimestamp() { 
    	return timestamp; 
    	}
    public String getLevel() { 
    	return level; 
    	}
    public String getModule() { 
    	return module; 
    	}
    public String getMessage() { 
    	return message; 
    	}
    public String getUser() { 
    	return user; 
    	}
    public String getSourceFile() { 
    	return sourceFile; 
    	}

    @Override
    public String toString() {
        return String.format("%s %s [%s] %s (User: %s) <%s>",
                timestamp, level, module, message,
                user == null ? "-" : user, sourceFile);
    }
}
 