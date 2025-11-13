import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogAnalyzer {
	// Using list collection to store all LogEntry objects
    private final List<LogEntry> entries;
    
// Parameterized Constructor-initializes entries list
    public LogAnalyzer(List<LogEntry> entries) {
        this.entries = entries;
    }
    
// Method : summaryByLevel()
// Technique : Stream API + Collectors .groupingBy()
    public Map<String, Long> summaryByLevel() {
        return entries.stream()
                .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));
    }

// Method : summaryByModule()
// Technique : Stream A + Collectors.groupingBy()
    public Map<String, Long> summaryByModule() {
        return entries.stream()
                .collect(Collectors.groupingBy(LogEntry::getModule, Collectors.counting()));
    }
    
// Method : userActivity()
// Technique : Sream API + Filtering + Map Collection
    public Map<String, Long> userActivity() {
        return entries.stream()
                .map(LogEntry::getUser)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    
// Method : mostErrorProneModule()
// Technique : Stream API + Filtering + Collectors + Optional class
    public Optional<String> mostErrorProneModule() {
        return entries.stream()
                .filter(e -> "ERROR".equalsIgnoreCase(e.getLevel()))
                .collect(Collectors.groupingBy(LogEntry::getModule, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
    
// Method : search()
// Technique : Stream API + Filter + List Collection
    public List<LogEntry> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) return Collections.emptyList();
        String key = keyword.toLowerCase();
        return entries.stream()
                .filter(e -> e.getMessage().toLowerCase().contains(key)
                          || e.getModule().toLowerCase().contains(key)
                          || (e.getUser() != null && e.getUser().toLowerCase().contains(key)))
                .collect(Collectors.toList());
    }
}