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
    - Update Book
    - Delete Book
    - Display all books (covered)
        - Sorting actions
    - count management
    - Book search by ID / Name / Title (covered)
    - Request book
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Books {
    public String bookName;
    public String authorName;
    public String uniqueId;
    public String category;
    public Boolean availability;
    public Integer stock;

    public static ArrayList<Books> booksList = new ArrayList<>();
    
    public Books() {}

    public Books(String bookName, String authorName, String uniqueId, String category, Boolean availability, Integer stock) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.uniqueId = uniqueId;
        this.category = category;
        this.availability = availability;
        this.stock = stock;
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

    public void addBook(String bookName, String authorName, String category, Boolean availability, Integer stock) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.uniqueId = "BOOKS" + System.currentTimeMillis() % 1_000_000_0;
        this.category = category;
        this.availability = availability;
        this.stock = stock;
        booksList.add(this);
    }

    public void displayAllBooks() {
        System.out.println();
        System.out.println("Books List:");
        for(int i = 0; i < booksList.size(); i++) {
            System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
        }
    }

    public void searchBookByName(Scanner sc) {
        Boolean flag = true;
        System.out.println("Enter a book name for search....");
        String searchedName = sc.nextLine();
        System.out.println("Searching....");
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getBookName().contains(searchedName)) {
                System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
                flag = false;
            }
        }
        if(flag) {
            System.out.println("No results found. Search with a different name.");
        }
    }

    public void searchBookByUniqueId(Scanner sc) {
        Boolean flag = true;
        System.out.println("Enter a Book ID for search....");
        String bookId = sc.nextLine();
        System.out.println("Searching....");
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getUniqueId().contains(bookId)) {
                System.out.println("Book Name: " + booksList.get(i).getBookName() + ", Author Name: " + booksList.get(i).getAuthorName() + ", Unique ID: " + booksList.get(i).getUniqueId() + ", Category: " + booksList.get(i).getCategory() + ", Availability: " + booksList.get(i).getAvailability() + ", Stock: " + booksList.get(i).getStock());
                flag = false;
            }
        }
        if(flag) {
            System.out.println("No results found. Search with a different name.");
        }
    }

    public void deleteBook(Scanner sc) {
        Boolean flag = true;
        String uniqueId = sc.nextLine();
        for(int i = 0; i < booksList.size(); i++) {
            if(booksList.get(i).getUniqueId().equals(uniqueId)) {
                System.out.println("Removing a book " + booksList.get(i).getBookName());
                booksList.remove(i);
                System.out.println("Book deleted successfully...");
                flag = false;
                break;
            }
        }
        if(flag) {
            System.out.println("No results found.");
        }
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