# Multi File Log Parser (Java)
 
This project reads multiple `.log` files from a directory, extracts important details using Regular Expressions (Regex), and generates useful summaries for log levels, modules, and user activity. It is a console-based Java application built using Core Java concepts and Object-Oriented Programming (OOP).
 
---
 
## Features
- Reads all `.log` files from a folder  
- Extracts:
  - Timestamp  
  - Log Level (INFO, WARN, ERROR)  
  - Module (AUTH, PAYMENT, SYSTEM, DB, etc.)
  - User (only if present)  
  - Message  
  - Source log file  
- Shows the following reports:
  1. Log Level Summary  
  2. Module Summary  
  3. User Activity Summary  
  4. Most Error-Prone Module  
  5. Search logs by keyword  
- Console-based menu for easy navigation  
- Works entirely with Core Java (no frameworks)
 
---
 
## Technologies Used
- **Java Core**
- **OOP (Encapsulation, Constructor, Method Overriding)**
- **Collections Framework (List, Map)**
- **Java Regex (Pattern, Matcher)**
- **File Handling (java.nio.file)**
- **Stream API + Lambda Expressions**
- **Exception Handling**
 
---
 
## Project Structure
    MultiFileLogParser/
       ├── Main.java 
       ├── LogEntry.java 
       ├── LogParser.java 
       └── LogAnalyzer.java 
              └── logs/ 
                   ├── auth.log 
                   ├── db.log 
                   ├── system.log 
                   └── payment.log

## How to Run
-Open the project in Eclipse
-Run Main.java
-Enter the folder path where your log files are stored
-Use the menu to view summaries

## Short Explanation
     This project reads all .log files from a folder and extracts important details from each line using regex patterns. The extracted data is stored in lists and maps, and different summaries like log levels, modules, and user activity are generated. The program is divided into separate classes for reading logs, analyzing data, and displaying a menu. All results are shown through a simple console-based menu system.

## Output
=== Multi-File Log Parser ===
Enter the folder path that contains .log files: logs
Parsed 16 log entries.

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 1

Log Level Summary:
ERROR: 8
INFO: 7
WARN: 1

Press Enter to continue...

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 2

Module Summary:
SYSTEM: 3
PAYMENT: 3
AUTH: 4
DB: 6

Press Enter to continue...

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 3

User Activity Summary:
Mike: 1 actions
David: 1 actions
John: 3 actions

Press Enter to continue...

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 4

Most Error-Prone Module:
Module with most ERROR logs: DB

Press Enter to continue...

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 5

Enter keyword to search: payment
2025-11-07 10:24:01 ERROR [PAYMENT] Payment failed: Timeout (User: -) <payment.log>
2025-11-07 11:14:18 INFO [PAYMENT] Payment processed for User John (User: John) <payment.log>
2025-11-07 11:18:43 ERROR [PAYMENT] Payment failed: Invalid Account Number (User: -) <payment.log>

Press Enter to continue...

Menu:
1. Show Log Level Summary
2. Show Module Summary
3. Show User Activity Summary
4. Show Most Error-Prone Module
5. Search Logs by Keyword
6. Exit
Enter your choice: 6
Program exited successfully!
