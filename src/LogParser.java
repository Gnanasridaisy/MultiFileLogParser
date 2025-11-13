import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {

    // Pattern to parse: YYYY-MM-DD HH:MM:SS LEVEL [MODULE] message
    private static final Pattern LINE_PATTERN = Pattern.compile(
        "^([0-9]{4}-[0-9]{2}-[0-9]{2}\\s+[0-9]{2}:[0-9]{2}:[0-9]{2})\\s+(INFO|WARN|ERROR|DEBUG)\\s+\\[([^\\]]+)\\]\\s+(.*)$"
    );

    // Pattern to extract User (handles “User John”, “for user John”, “User: John”)
    private static final Pattern USER_PATTERN = Pattern.compile(
        "(?i)(?:for\\s+)?user\\s*[:\\-]?\\s*([A-Za-z0-9_.-]+)"
    );
    
// Method : parseFile()
// Technique: File Reading using Stream API (Files.lines())
    public List<LogEntry> parseFile(Path filePath) throws IOException {
    	// Using list collection to store all log entries from one file
        List<LogEntry> entries = new ArrayList<>();
        String source = filePath.getFileName().toString();

        try (Stream<String> lines = Files.lines(filePath)) {
        	// Using lambda expression with forEachOrdered() 
            lines.forEachOrdered(line -> {
            	// Using Matcher class to apply LINE_PATTERN Regex on each line
                Matcher m = LINE_PATTERN.matcher(line);
                if (m.matches()) {
                	// Extracting each group from the pattern
                    String timestamp = m.group(1);
                    String level = m.group(2);
                    String module = m.group(3);
                    String message = m.group(4);
                    
// Using another Matcher to find "user" keyword in the line
                    String user = null;
                    Matcher um = USER_PATTERN.matcher(line);
                    if (um.find()) user = um.group(1);
                    
// Creating LogEntry object
                    entries.add(new LogEntry(timestamp, level, module, message, user, source));
                }
            });
        }
        return entries;
    }
    
// Method : parseDirectory()
// Technique : Directory traversing using stream API and Files.list()
    public List<LogEntry> parseDirectory(Path dir) throws IOException {
    	// List to hold all log entries from multiple files
        List<LogEntry> all = new ArrayList<>();
        // Using Stream to iterate overall files in directory
        try (Stream<Path> files = Files.list(dir)) {
        	// Using lambda expression 
            files.filter(f -> f.toString().toLowerCase().endsWith(".log"))
                 .forEach(f -> {
                     try {
                         all.addAll(parseFile(f));
                     } catch (IOException e) {
                         System.err.println("Error reading " + f + ": " + e.getMessage());
                     }
                 });
        }
        return all;
    }
}