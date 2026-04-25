package Library;

import java.util.InputMismatchException;
import java.util.Scanner;

import Library.Constants.BOOKS;
import Library.Constants.MEMBERS;

public class ConsoleInteraction extends Books {

    public Members member = new Members();

    public String getBookName(Scanner sc) {
        System.out.print("Enter the book name: ");
        String bookName;
        while(true) {
            bookName = sc.nextLine();
            if(bookName.isEmpty()) {
                System.out.print("Book name cannot be empty. Please enter again: ");
            }
            else if(validateBookDetails(BOOKS.BOOK_NAME, bookName)) {
                System.out.print("Book name already exists. Please enter again: ");
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
        book.addBook(bookName, authorName, category, true, stock);

        System.out.println("\nBook added successfully with details: ");
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Author Name: " + book.getAuthorName());
        System.out.println("Unique ID: " + book.getUniqueId());
        System.out.println("Category: " + book.getCategory());
        System.out.println("Availability: " + book.getAvailability());
        System.out.println("Stock: " + book.getStock());
        System.out.println("---------------------------------------------");
    }

    public String getMemberName(Scanner sc) {
        System.out.print("Enter the member name: ");
        String memberName;
        while(true) {
            memberName = sc.nextLine();
            if(memberName.isEmpty()) {
                System.out.print("Member name cannot be empty. Please enter again: ");
            }
            else if(member.validateDetails(MEMBERS.MEMBER_NAME, memberName)) {
                System.out.print("Member name already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return memberName;
    }

    public String getPhoneNumber(Scanner sc) {
        System.out.print("Enter the phone number: ");
        String phoneNumber;
        while(true) {
            phoneNumber = sc.nextLine();
            if(phoneNumber.isEmpty()) {
                System.out.print("Phone number cannot be empty. Please enter again: ");
            }
            else if(!phoneNumber.matches("\\d{10}")) {
                System.out.print("Phone number should be 10 digits. Please enter again: ");
            }
            else if(member.validateDetails(MEMBERS.PHONE_NUMBER, phoneNumber)) {
                System.out.print("Phone number already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return phoneNumber;
    }

    public String getJoiningDate(Scanner sc) {
        System.out.print("Enter the joining date (YYYY-MM-DD): ");
        String joiningDate;
        while(true) {
            joiningDate = sc.nextLine();
            if(joiningDate.isEmpty()) {
                System.out.print("Joining date cannot be empty. Please enter again: ");
            }
            else if(!joiningDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.print("Joining date should be in the format YYYY-MM-DD. Please enter again: ");
            } 
            else {
                break;
            }
        }
        return joiningDate;
    }

    public Members addMember(Scanner sc) {
        System.out.println("Adding a new member...");
        String memberName = getMemberName(sc);
        String phoneNumber = getPhoneNumber(sc);
        String joiningDate = getJoiningDate(sc); // add logic for auto update

        Members member = new Members();
        member.addMember(memberName, phoneNumber, joiningDate);

        System.out.println("\nMember added successfully with details: ");
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Phone Number: " + member.getPhoneNumber());
        System.out.println("Joining Date: " + member.getJoiningDate());

        return member;
    }

    public void displayAdminMenu(Scanner sc) {
        try {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Search a book by name");
            System.out.println("4. Search a book by unique ID");
            System.out.println("5. Update book details");
            System.out.println("6. Delete a book");
            System.out.println("7. Display all members");
            System.out.println("8. Update member details");
            System.out.println("9. Logout");

            Integer userInput = sc.nextInt();
            switch(userInput) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    searchBookByName(sc);
                    break;
                case 4:
                    searchBookByUniqueId(sc);
                    break;
                case 5:
                    updateBookDetails(sc);
                    break;
                case 6:
                    deleteBook(sc);
                    break;
                case 7:
                    member.displayAllMembers();
                    break;
                case 8:
                    member.updateMemberDetails(sc);
                    break;
                case 9:
                    System.out.println("Logging out...");
                    Main.enterTheApplication();
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option from the menu.");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
            sc.nextLine(); // Consume the invalid input
        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        displayAdminMenu(sc); // Re-display the menu 
    }
    public void displayMemberMenu(Scanner sc) {
        try {
            System.out.println("\nMember Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Search a book by name");
            System.out.println("3. Search a book by unique ID");
            System.out.println("4. Logout");

            System.out.print("\nEnter your choice: ");
            Integer userInput = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            switch(userInput) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    searchBookByName(sc);
                    break;
                case 3:
                    searchBookByUniqueId(sc);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    Main.enterTheApplication();
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option from the menu.");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        displayMemberMenu(sc); // Re-display the menu 
    }

}