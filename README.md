# Library Management System

A console-based Library Management System implemented in Java. The application supports two user roles — **Admin** and **Member** — and persists all data using CSV files.

---

## Table of Contents

- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Application Flow](#application-flow)
- [Source Files](#source-files)
  - [Main.java](#mainjava)
  - [Books.java](#booksjava)
  - [Members.java](#membersjava)
  - [ConsoleInteraction.java](#consoleinteractionjava)
  - [Constants.java](#constantsjava)
  - [StoreData.java](#storedatajava)
- [Data Storage](#data-storage)
- [Features](#features)

---

## Project Structure

```
LibraryManagementSystem/
├── src/
│   └── Library/
│       ├── Main.java
│       ├── Books.java
│       ├── Members.java
│       ├── ConsoleInteraction.java
│       ├── Constants.java
│       ├── StoreData.java
│       └── DataStore/
│           ├── adminData.csv
│           ├── booksData.csv
│           ├── borrowedData.csv
│           ├── membersData.csv
│           └── reservedData.csv
└── bin/
    └── Library/
        └── DataStore/
            └── (compiled output mirrors src structure)
```

---

## How to Run

**Compile:**
```bash
javac -d bin src/Library/*.java
```

**Run:**
```bash
java -cp bin Library.Main
```

---

## Application Flow

1. On startup, all CSV data files are loaded into memory (`restoreData`).
2. The user is prompted to log in as an **Admin**, an existing **Member**, or to **register** as a new member.
3. After authentication, the appropriate menu (Admin or Member) is displayed.
4. On exit or logout, all in-memory data is written back to the CSV files (`storeData`).

---

## Source Files

### Main.java

The entry point of the application. Responsibilities:

- Declares shared static instances of `Books`, `Members`, `ConsoleInteraction`, and `Scanner`.
- Sets the `dataFilePath` to `src/Library/DataStore/` relative to the working directory.
- **`main()`** — Orchestrates the lifecycle: restore data → enter application → store data → exit.
- **`enterTheApplication()`** — Calls `login()` and routes the authenticated user to the admin or member menu based on whether they exist in `Members.adminList`.
- **`login()`** — Handles three login paths:
  - **Admin login**: prompts for Admin ID and password, matches against `Members.adminList`.
  - **Member login**: prompts for Member ID and phone number, matches against `Members.membersList`.
  - **New registration**: delegates to `ConsoleInteraction.addMemberFromConsole()`.
- **`restoreData()`** / **`storeData()`** — Instantiate `StoreData` and call all five restore/store methods (books, members, borrowed, reserved, admin).

---

### Books.java

Extends `ConsoleInteraction`. Represents a single book and manages the global book list.

**Fields:**

|         Field          |          Type        |                 Description               |
|------------------------|----------------------|-------------------------------------------|
| `bookName`             | `String`             | Title of the book                         |
| `authorName`           | `String`             | Name of the author                        |
| `uniqueId`             | `String`             | Auto-generated ID prefixed with `BOOKS`   |
| `category`             | `String`             | Genre / subject category                  |
| `availability`         | `Boolean`            | `true` if stock > 0                       |
| `stock`                | `Integer`            | Number of copies available                |
| `borrowedDate`         | `String`             | Date the book was borrowed (ISO format)   |
| `dueDate`              | `String`             | Return due date (2 weeks from borrow date)|
| `returnedDate`         | `String`             | Actual return date                        |
| `borrowedByMembers`    | `ArrayList<Members>` | Members currently holding this book       |
| `reservedByMembers`    | `ArrayList<Members>` | Members who have reserved this book       |
| `booksList` *(static)* | `ArrayList<Books>`   | Global list of all books                  |

**Key Methods:**

- **`addBook()`** — Initialises all fields and adds the book to `booksList`. The unique ID is generated using `"BOOKS" + System.currentTimeMillis() % 10_000_000`.
- **`displayAllBooks()`** — Prints all books with their details to the console.
- **`searchBookByName()`** — Case-sensitive substring search; loops until a match is found.
- **`searchBookByUniqueId()`** — Searches by the `BOOKS`-prefixed unique ID.
- **`getBookByUniqueId()`** — Returns the `Books` object matching the given ID, or `null`.
- **`deleteBook()`** — Removes a book from `booksList` by unique ID.
- **`updateBookDetails()`** — Updates name, author, category, and/or stock (fields are skippable).
- **`validateBookDetails()`** — Checks for duplicate book name or unique ID using the `BOOKS` enum from `Constants`.
- **`setStock()`** — Also updates `availability` automatically (`stock > 0`).

---

### Members.java

Extends `ConsoleInteraction`. Represents a library member or admin and manages global member/admin lists.

**Fields:**

|         Field             |          Type        |                 Description               |
|---------------------------|----------------------|-------------------------------------------|
| `memberName`              | `String`             | Full name of the member                   |
| `password`                | `String` (private)   | Password — used only for admins           |
| `memberId`                | `String`             | Auto-generated ID prefixed with `MEMBER` or `ADMIN` |
| `phoneNumber`             | `String`             | 10-digit phone number                     |
| `joiningDate`             | `String`             | ISO date string set at registration       |
| `borrowedBooks`           | `ArrayList<Books>`   | Books currently borrowed by this member   |
| `reservedBooks`           | `ArrayList<Books>`   | Books reserved by this member             |
| `membersList` *(static)*  | `ArrayList<Members>` | Global list of all regular members        |
| `adminList` *(static)*    | `ArrayList<Members>` | Global list of all admins                 |

**Key Methods:**

- **`addMember()`** — Sets member fields, generates `MEMBER`-prefixed ID, adds to `membersList`.
- **`addAdmin()`** — Same as `addMember()` but stores a password and generates an `ADMIN`-prefixed ID; adds to `adminList`.
- **`removeAdmin()`** — Prompts for Admin ID and removes from `adminList`.
- **`borrowBook()`** — Looks up a book by ID; if available, sets borrow and due dates (2 weeks), decrements stock, and records the association on both the book and the member.
- **`returnBook()`** — Sets the returned date to today, increments stock, and removes the book from the member's borrowed list.
- **`displayMemberDetails()`** — Prints all member fields and their borrowed/reserved book lists.
- **`displayAllMembers()`** — Lists all members with name and ID.
- **`updateMemberDetails()`** — Allows updating name and phone number by member ID.
- **`deleteMember()`** — Removes a member from `membersList` by ID.
- **`searchMemberByMemberId()`** — Returns the `Members` object matching the ID and prints details.
- **`getMemberByMemberId()`** — Utility lookup returning a `Members` object or `null`.
- **`validateDetails()`** — Checks for duplicates across name, ID, or phone number using the `MEMBERS` enum.

---

### ConsoleInteraction.java

Base class for both `Books` and `Members`. Centralises all console I/O logic and menu navigation.

**Responsibilities:**

- Holds a shared `static Scanner sc` used throughout the application.
- Provides validated input methods for every data field (book name, author, category, stock, member name, ID, phone number, joining date, password). Each method loops until valid input is supplied.
- **`userConfirmation()`** — Generic Y/N prompt returning a `Boolean`.
- **`addBookFromConsole()`** — Collects book details interactively, creates a new `Books` instance, and prints a confirmation.
- **`addMemberFromConsole()`** — Collects member details and calls `member.addMember()`.
- **`addAdminFromConsole()`** / **`removeAdminFromConsole()`** — Delegate to `Members.addAdmin()` / `Members.removeAdmin()`.
- **`displayAdminMenu()`** — Renders a 13-option menu and dispatches to the appropriate action. Recursively re-displays after each action. Options include book CRUD, member management, admin management, logout, and exit.
- **`displayMemberMenu()`** — Renders a 7-option menu (display books, search books, borrow, return, logout, exit). Recursively re-displays after each action.

**Input Validation Highlights:**

- Phone number must be exactly 10 digits (`\d{10}`).
- Joining date must match `YYYY-MM-DD` (`\d{4}-\d{2}-\d{2}`).
- Password must be at least 6 characters.
- Book unique IDs must start with `BOOKS`.
- Duplicate book names and duplicate member phone numbers are rejected at entry time.

---

### Constants.java

Defines two enums used throughout the application for type-safe field identification:

```java
public enum MEMBERS {
    MEMBER_NAME, MEMBER_ID, PHONE_NUMBER, JOINING_DATE
}

public enum BOOKS {
    BOOK_NAME, AUTHOR_NAME, UNIQUE_ID, CATEGORY, AVAILABILITY, STOCK
}
```

Each enum constant carries a human-readable `displayName` string accessible via `getDisplayName()`. These enums are passed to `validateBookDetails()` and `validateDetails()` to specify which field to check for duplicates.

---

### StoreData.java

Handles all CSV-based persistence. Takes `Books` and `Members` instances in its constructor for cross-referencing during restoration.

**Store Methods** (write in-memory data to CSV):

|        Method         |         File       |                          Columns                           |
|-----------------------|--------------------|------------------------------------------------------------|
| `storeBooksData()`    | `booksData.csv`    | Book Name, Author Name, Unique ID, Category, Stock         |
| `storeMembersData()`  | `membersData.csv`  | Member Name, Member ID, Phone Number, Joining Date         |
| `storeAdminData()`    | `adminData.csv`    | Admin Name, Admin ID, Password, Phone Number, Joining Date |
| `storeBorrowedData()` | `borrowedData.csv` | Member ID, Book ID, Borrow Date, Return Date, Due Date     |
| `storeReservedData()` | `reservedData.csv` | Member ID, Book ID, Borrow Date, Return Date, Due Date     |

**Restore Methods** (read CSV back into memory on startup):

Each restore method reads the corresponding CSV line by line, splits on commas, reconstructs the objects, and re-links relationships (e.g., associating borrowed books back to members and vice versa).

**Utility Methods:**

- **`verifyFileExistence()`** — Checks if the CSV file exists; creates it if missing.
- **`writeDataInFile()`** — Writes a string to a file using `BufferedOutputStream`, overwriting existing content.

---

## Data Storage

All data is stored as plain CSV files under `src/Library/DataStore/`:

|          File       |             Contents                    |
|---------------------|-----------------------------------------|
| `booksData.csv`     | Master list of all books                |
| `membersData.csv`   | Registered library members              |
| `adminData.csv`     | Admin accounts (includes hashed/plain passwords) |
| `borrowedData.csv`  | Active and historical borrow records    |
| `reservedData.csv`  | Active reservation records              |

Data is loaded on every application start and saved on every clean exit or logout.

---

## Features

### Admin
- Add, update, delete, and display all books
- Search books by name or unique ID
- Display all members and search by member ID
- Update member details
- Add and remove admin accounts
- Logout (returns to login screen) or exit the application

### Member
- View all available books
- Search books by name or unique ID
- Borrow a book (sets a 2-week due date, decrements stock)
- Return a borrowed book (restores stock)
- Logout or exit the application

### General
- New users can self-register during login
- Duplicate detection for book names, member names, IDs, and phone numbers
- All data persisted across sessions via CSV files
- Input validation with re-prompt loops for all user inputs