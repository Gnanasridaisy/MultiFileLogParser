import java.io.IOException;
import java.nio.file.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
    	// Using Scanner class to take user input
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Multi-File Log Parser ===");
        System.out.print("Enter the folder path that contains .log files: ");
        // Reading directory path from user
        String dirPath = sc.nextLine().trim();
 
        Path dir = Paths.get(dirPath);
        if (!Files.isDirectory(dir)) {
            System.out.println("Invalid folder path!");
            return;
        }
 // Object creation and method calling
        LogParser parser = new LogParser();
        List<LogEntry> entries;
        try {
            entries = parser.parseDirectory(dir);
        } catch (IOException e) {
            System.out.println("Error reading logs: " + e.getMessage());
            return;
        }
        
        // Creating object for LogAnalyzer
        LogAnalyzer analyzer = new LogAnalyzer(entries);
        System.out.println("Parsed " + entries.size() + " log entries.\n");
 
        boolean running = true;
        while (running) {
        	// Displaying menu options
            System.out.println("Menu:");
            System.out.println("1. Show Log Level Summary");
            System.out.println("2. Show Module Summary");
            System.out.println("3. Show User Activity Summary");
            System.out.println("4. Show Most Error-Prone Module");
            System.out.println("5. Search Logs by Keyword");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            // Reading user choice
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\nLog Level Summary:");
                    analyzer.summaryByLevel().forEach((k,v)->System.out.println(k+": "+v));
                    break;
 
                case "2":
                    System.out.println("\nModule Summary:");
                    analyzer.summaryByModule().forEach((k,v)->System.out.println(k+": "+v));
                    break;
 
                case "3":
                    System.out.println("\nUser Activity Summary:");
                    Map<String,Long> users = analyzer.userActivity();
                    if (users.isEmpty()) System.out.println("No user activity found.");
                    else users.forEach((k,v)->System.out.println(k+": "+v+" actions"));
                    break;
 
                case "4":
                    System.out.println("\nMost Error-Prone Module:");
                    analyzer.mostErrorProneModule().ifPresentOrElse(
                        m -> System.out.println("Module with most ERROR logs: "+m),
                        () -> System.out.println("No ERROR logs found.")
                    );
                    break;
 
                case "5":
                    System.out.print("\nEnter keyword to search: ");
                    String keyword = sc.nextLine();
                    List<LogEntry> results = analyzer.search(keyword);
                    if (results.isEmpty()) System.out.println("No matching logs found.");
                    else results.forEach(System.out::println);
                    break;
 
                case "6":
                    running = false;
                    System.out.println("Program exited successfully!");
                    break;
 
                default:
                    System.out.println("Invalid choice. Please enter between 1 and 6.");
            }
 
            if (running) {
                System.out.println("\nPress Enter to continue...");
                sc.nextLine();
            }
        }
        // Closing scanner
 
        sc.close();
    }
}