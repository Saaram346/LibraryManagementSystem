package Library;

import java.util.Scanner;

public class ConsoleInteraction {

    public String getBookName(Scanner sc) {
        System.out.print("Enter the book name: ");
        String bookName;
        while(true) {
            bookName = sc.nextLine();
            if(bookName.isEmpty()) {
                System.out.print("Book name cannot be empty. Please enter again: ");
            }
            else {
                break;
            }
        }
        return bookName;
    }

    public String getAuthorName(Scanner sc) {
        System.out.print("Enter the author name: ");
        String authorName;
        while(true) {
            authorName = sc.nextLine();
            if(authorName.isEmpty()) {
                System.out.print("Author name cannot be empty. Please enter again: ");
            }
            else {
                break;
            }
        }
        return authorName;
    }

    public String getUniqueIdOfBook(Scanner sc) {
        System.out.print("Enter the unique ID: ");
        String uniqueId;
        while(true) {
            uniqueId = sc.nextLine();
            if(uniqueId.isEmpty()) {
                System.out.print("Unique ID cannot be empty. Please enter again: ");
            }
            else if(!uniqueId.startsWith("BOOKS")) {
                System.out.print("Unique ID should start with 'BOOKS'. Please enter again: ");
            } 
            else {
                break;
            }
        }
        return uniqueId;
    }

    public String getBookCategory(Scanner sc) {
        System.out.print("Enter the category: ");
        String category;
        while(true) {
            category = sc.nextLine();
            if(category.isEmpty()) {
                System.out.print("Category cannot be empty. Please enter again: ");
            }
            else {
                break;
            }
        }
        return category;
    }

    public Integer getBookStock(Scanner sc) {
        System.out.print("Enter the stock quantity: ");
        Integer stock = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        return stock;
    }

    public void addBook(Scanner sc) {
        System.out.println("Adding a new book...");
        String bookName = getBookName(sc);
        String authorName = getAuthorName(sc);
        String category = getBookCategory(sc);
        Integer stock = getBookStock(sc);

        Books book = new Books();
        book.addBookDetails(bookName, authorName, category, true, stock);

        System.out.println("Book added successfully with details: ");
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Author Name: " + book.getAuthorName());
        System.out.println("Unique ID: " + book.getUniqueId());
        System.out.println("Category: " + book.getCategory());
        System.out.println("Availability: " + book.getAvailability());
        System.out.println("Stock: " + book.getStock());
    }

}