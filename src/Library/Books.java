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
    - Add Book
    - Update Book
    - Delete Book
    - Display all books
        - Sorting actions
    - count management
    - Book search by ID / Name / Title
    - Request book
*/

import java.util.ArrayList;

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

    public void addBookDetails(String bookName, String authorName, String category, Boolean availability, Integer stock) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.uniqueId = "BOOKS" + System.currentTimeMillis();
        this.category = category;
        this.availability = availability;
        this.stock = stock;
        booksList.add(this);
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
Date: <>
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
Date: <>
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
Date: <>
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

*/