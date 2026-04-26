package Library;
/*
Console Application: Library management system

Required components:
books, members, attendance

Books:
    Details:
        - Book name
        - Author
        - Title
        - ID
        - Category
        - Edition
        - Availability
        - Language
        - Count
Members:
    - Name
    - ID
    - Mail, Ph.No
    - Joining Date
    - Address

Attendance:
    - Book get, return and due date
    - Fine amount after due
    - book list based on member
    - members list based on book

Operations:

Books:
    - Add Book (covered)
    - Update Book (covered)
    - Delete Book (covered)
    - Display all books (covered)
        - Sorting actions
    - count management
    - Book search by ID / Name / Title (covered)
    - Request book
*/

import java.util.ArrayList;

import Library.Constants.BOOKS;

public class Books extends ConsoleInteraction {
    public String bookName;
    public String authorName;
    public String uniqueId;
    public String category;
    public Boolean availability;
    public Integer stock;
    public String borrowedDate;
    public String dueDate;
    public String returnedDate;
    public ArrayList<Members> borrowedByMembers = new ArrayList<>();
    public ArrayList<Members> reservedByMembers = new ArrayList<>();

    public static ArrayList<Books> booksList = new ArrayList<>();
    
    public Books() {}

    public Books(String bookName, String authorName, String category, Integer stock) {
        setBookName(bookName);
        setAuthorName(authorName);
        setUniqueId("BOOKS" + System.currentTimeMillis() % 1_000_000_0);
        setCategory(category);
        setStock(stock);
        booksList.add(this);
        this.borrowedByMembers = new ArrayList<>();
        this.reservedByMembers = new ArrayList<>();
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookName() {
        return this.bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorName() {
        return this.authorName;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Boolean getAvailability() {
        return this.availability;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        this.availability = (this.stock > 0);
    }
    public Integer getStock() {
        return this.stock;
    }

    public void setBorrowedByMembers(Members member) {
        this.borrowedByMembers.add(member);
    }
    public void removeBorrowedByMembers(Members member) {
        this.borrowedByMembers.remove(member);
    }

    public void addReservedByMembers(Members member) {
        this.reservedByMembers.add(member);
    }
    public ArrayList<Members> getBorrowedByMembers() {
        return this.borrowedByMembers;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
    public String getBorrowedDate() {
        return this.borrowedDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getDueDate() {
        return this.dueDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }
    public String getReturnedDate() {
        return this.returnedDate;
    }

    public void addBook(String bookName, String authorName, String category, Boolean availability, Integer stock) {
        setBookName(bookName);
        setAuthorName(authorName);
        setUniqueId("BOOKS" + System.currentTimeMillis() % 1_000_000_0);
        setCategory(category);
        setAvailability(availability);
        setStock(stock);
        booksList.add(this);
        this.borrowedByMembers = new ArrayList<>();
        this.reservedByMembers = new ArrayList<>();
    }

    public void displayAllBooks() {
        System.out.println("\nBooks List:");
        if(booksList.isEmpty()) {
            System.out.println("No books available in the library.\n");
            return;
        }
        for(int i = 0; i < booksList.size(); i++) {
            System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
        }
    }

    public void searchBookByName() {
        Boolean flag = true;
        while(true) {
            System.out.println("\nEnter a book name for search....");
            String searchedName = getBookNameFromConsole(false, true);
            System.out.println("Searching....");
            for(int i = 0; i < booksList.size(); i++) {
                if(booksList.get(i).getBookName().contains(searchedName)) {
                    System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
                    flag = false;
                }
            }
            if(flag) {
                System.out.println("No results found. Search with a different name.\n");
            }
            else {
                break;
            }
        }
    }

    public void searchBookByUniqueId() {
        Boolean flag = true;
        System.out.println("\nEnter a Book ID for search....");
        String bookId = getUniqueIdOfBookFromConsole();
        System.out.println("Searching....");
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getUniqueId().contains(bookId)) {
                System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
                flag = false;
            }
        }
        if(flag) {
            System.out.println("No results found. Search with a different name.\n");
        }
    }

    public Books getBookByUniqueId(String uniqueId) {
        for(Books book : booksList) {
            if(book.getUniqueId().equalsIgnoreCase(uniqueId)) {
                return book;
            }
        }
        return null;
    }

    public void deleteBook() {
        System.out.println("\nEnter a Book ID to delete the book....");
        String uniqueId = getUniqueIdOfBookFromConsole();
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getUniqueId().equals(uniqueId)) {
                System.out.println("Removing a book " + booksList.get(i).getBookName());
                booksList.remove(i);
                System.out.println("Book deleted successfully...\n");
                return;
            }
        }
        System.out.println("No results found.\n");
    }

    public void updateBookDetails() {
        System.out.println("\nEnter a Book ID to update the book....");
        String uniqueId = getUniqueIdOfBookFromConsole();
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getUniqueId().equals(uniqueId)) {
                System.out.println("Current " + booksList.get(i).getBookName());
                System.out.println("Enter a updated book name or press enter to skip: ");
                String updatedBookName = getBookNameFromConsole(false, false);
                if(!updatedBookName.isEmpty()) {
                    booksList.get(i).setBookName(updatedBookName);
                    System.out.println("Book Name updated successfully");
                }
                System.out.println("Enter a updated author name or press enter to skip: ");
                String updatedAuthorName = getAuthorNameFromConsole(false);
                if(!updatedAuthorName.isEmpty()) {
                    booksList.get(i).setAuthorName(updatedAuthorName);
                    System.out.println("Author Name updated successfully");
                }
                System.out.println("Enter a updated category or press enter to skip: ");
                String updatedCategory = getBookCategoryFromConsole(false);
                if(!updatedCategory.isEmpty()) {
                    booksList.get(i).setCategory(updatedCategory);
                    System.out.println("Category updated successfully");
                }
                System.out.println("Enter a updated stock or press enter to skip: ");
                Integer updatedStock = getBookStockFromConsole();
                if(updatedStock != null) {
                    booksList.get(i).setStock(updatedStock);
                    System.out.println("Stock updated successfully");
                }
                System.out.println("Book details updated successfully with details: ");
                System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock() + "\n");
                return;
            }
        }
        System.out.println("No results found.\n");
    }

    public Boolean validateBookDetails(BOOKS type, String content) {
        for(Books book : booksList) {
            if(type == BOOKS.BOOK_NAME && book.getBookName().equals(content)) {
                return true;
            }
            else if(type == BOOKS.UNIQUE_ID && book.getUniqueId().equals(content)) {
                return true;
            }
        }
        return false;
    }


    
}

/*

Variables 
Methods
Constructors

Interface
Blocks

getter setter
constructor

OOPS

Encapsulation -> 
Inheritance
Polymorphism
Abstraction
Class and Objects


Duplicate name
null



Original
static: 3
Date: <> 10
Name:
Age:
Gender:
College name: <>
Register No: <>
DOB:
Ph No:
College Ph No: <>
College Address: <>
Address:


X1 - addd
static: 1 + 1 = 3
Date: <> 10 + 1 = 11
Name: ###
Age:###
Gender:###
College name: <>
Register No: <>
DOB:###
Ph No:###
College Ph No: <>
College Address: <>
Address:###

X2
static: 2 + 1 = 3
Date: <> 10 + 2 = 12
Name:***
Age:***
Gender:***
College name: <>
Register No: <>
DOB:***
Ph No:***
College Ph No: <>
College Address: <>
Address:***

Today’s Task:
Imagine, Library has 10 books. (we already discussed about where books are stored)
Generate a method to display all books in the console.
*/