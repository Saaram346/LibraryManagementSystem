package Library;

import java.util.InputMismatchException;
import java.util.Scanner;

import Library.Constants.BOOKS;
import Library.Constants.MEMBERS;

public class ConsoleInteraction {

    public Members member;
    public Books book;
    public static Scanner sc;

    public ConsoleInteraction() {}
    public ConsoleInteraction(Books book, Members member) {
        this.member = member;
        this.book = book;
    }

    public static Boolean userConfirmation(String action) {
        System.out.println(action + " (Y/N)");
        String confirmation = sc.next();
        sc.nextLine(); // Consume the newline character
        return confirmation.equalsIgnoreCase("Y");
    }

    public String getBookNameFromConsole(Boolean isValidate, Boolean emptyCheck) {
        System.out.print("Enter the book name: ");
        String bookName;
        while(true) {
            bookName = sc.nextLine();
            if(emptyCheck && bookName.isEmpty()) {
                System.out.print("Book name cannot be empty. Please enter again: ");
            }
            else if(isValidate && book.validateBookDetails(BOOKS.BOOK_NAME, bookName)) {
                System.out.print("Book name already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return bookName;
    }

    public String getAuthorNameFromConsole(Boolean emptyCheck) {
        System.out.print("Enter the author name: ");
        String authorName;
        while(true) {
            authorName = sc.nextLine();
            if(emptyCheck && authorName.isEmpty()) {
                System.out.print("Author name cannot be empty. Please enter again: ");
            }
            else {
                break;
            }
        }
        return authorName;
    }

    public String getUniqueIdOfBookFromConsole() {
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

    public String getBookCategoryFromConsole(Boolean emptyCheck) {
        System.out.print("Enter the category: ");
        String category;
        while(true) {
            category = sc.nextLine();
            if(emptyCheck && category.isEmpty()) {
                System.out.print("Category cannot be empty. Please enter again: ");
            }
            else {
                break;
            }
        }
        return category;
    }

    public Integer getBookStockFromConsole() {
        System.out.print("Enter the stock quantity: ");
        Integer stock = null;
        try {
            stock = sc.nextInt();
            sc.nextLine(); // Consume the newline character
        } 
        catch (InputMismatchException e) {
            sc.nextLine(); // Consume the newline character
            System.out.println("Invalid input. Please enter a number.");
            getBookStockFromConsole();
        }
        return stock;
    }

    public void addBookFromConsole() {
        System.out.println("Adding a new book...");
        String bookName = getBookNameFromConsole(true, true);
        String authorName = getAuthorNameFromConsole(true);
        String category = getBookCategoryFromConsole(true);
        Integer stock = getBookStockFromConsole();

        Books book = new Books();
        book.addBook(bookName, authorName, category, true, stock);

        System.out.println("---------------------------------------------");
        System.out.println("\nBook added successfully with details: ");
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Author Name: " + book.getAuthorName());
        System.out.println("Unique ID: " + book.getUniqueId());
        System.out.println("Category: " + book.getCategory());
        System.out.println("Availability: " + book.getAvailability());
        System.out.println("Stock: " + book.getStock());
        System.out.println("---------------------------------------------");
    }

    public String getMemberNameFromConsole(Boolean emptyCheck, Boolean isValidate) {
        System.out.print("Enter the member name: ");
        String memberName;
        while(true) {
            memberName = sc.nextLine();
            if(emptyCheck && memberName.isEmpty()) {
                System.out.print("Member name cannot be empty. Please enter again: ");
            }
            else if(isValidate && member.validateDetails(MEMBERS.MEMBER_NAME, memberName)) {
                System.out.print("Member name already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return memberName;
    }

    public String getMemberIdFromConsole(Boolean emptyCheck, Boolean isValidate) {
        System.out.print("Enter the member ID: ");
        String memberId;
        while(true) {
            memberId = sc.nextLine();
            if(emptyCheck && memberId.isEmpty()) {
                System.out.print("Member ID cannot be empty. Please enter again: ");
            }
            else if(isValidate && member.validateDetails(MEMBERS.MEMBER_ID, memberId)) {
                System.out.print("Member ID already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return memberId;
    }

    public String getPhoneNumberFromConsole(Boolean emptyCheck, Boolean isValidate) {
        System.out.print("Enter the phone number: ");
        String phoneNumber;
        while(true) {
            phoneNumber = sc.nextLine();
            if(emptyCheck && phoneNumber.isEmpty()) {
                System.out.print("Phone number cannot be empty. Please enter again: ");
            }
            else if(!phoneNumber.matches("\\d{10}")) {
                System.out.print("Phone number should be 10 digits. Please enter again: ");
            }
            else if(isValidate && member.validateDetails(MEMBERS.PHONE_NUMBER, phoneNumber)) {
                System.out.print("Phone number already exists. Please enter again: ");
            }
            else {
                break;
            }
        }
        return phoneNumber;
    }

    public String getJoiningDate() {
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

    public Members addMemberFromConsole(Members member) {
        System.out.println("Adding a new member...");
        String memberName = getMemberNameFromConsole(true, true);
        String phoneNumber = getPhoneNumberFromConsole(true, true);

        member.addMember(memberName, phoneNumber);

        System.out.println("\nMember added successfully with details: ");
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Phone Number: " + member.getPhoneNumber());
        System.out.println("Joining Date: " + member.getJoiningDate());

        return member;
    }

    public void addAdminFromConsole() {
        System.out.println("Adding a new admin...");
        String memberName = getMemberNameFromConsole(true, true);
        String phoneNumber = getPhoneNumberFromConsole(true, true);

        Members admin = new Members();
        admin.addAdmin(memberName, phoneNumber, getPasswordFromConsole());

        System.out.println("\nAdmin added successfully with details: ");
        System.out.println("Admin Name: " + admin.getMemberName());
        System.out.println("Admin ID: " + admin.getMemberId());
        System.out.println("Phone Number: " + admin.getPhoneNumber());
        System.out.println("Joining Date: " + admin.getJoiningDate());
    }

    public void removeAdminFromConsole() {
        System.out.println("Removing an admin...");
        Members admin = new Members();
        admin.removeAdmin();
    }

    public String getPasswordFromConsole() {
        System.out.print("Enter the password: ");
        String password;
        while(true) {
            password = sc.nextLine();
            if(password.isEmpty()) {
                System.out.print("Password cannot be empty. Please enter again: ");
            }
            else if(password.length() < 6) {
                System.out.print("Password should be at least 6 characters long. Please enter again: ");
            }
            else {
                break;
            }
        }
        return password;
    }

    public void displayAdminMenu(Members member) {
        try {
            System.out.println("\n---------------------------------------------");
            System.out.println("Admin Menu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Search a book by name");
            System.out.println("4. Search a book by unique ID");
            System.out.println("5. Update book details");
            System.out.println("6. Delete a book");
            System.out.println("7. Display all members");
            System.out.println("8. Search member details using member ID");
            System.out.println("9. Update member details");
            System.out.println("10. Add Admin");
            System.out.println("11. Remove Admin");
            System.out.println("12. Logout");
            System.out.println("13. Exit");

            System.out.println("\nEnter your choice: ");
            Integer userInput = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            switch(userInput) {
                case 1:
                    addBookFromConsole();
                    break;
                case 2:
                    book.displayAllBooks();
                    break;
                case 3:
                    book.searchBookByName();
                    break;
                case 4:
                    book.searchBookByUniqueId();
                    break;
                case 5:
                    book.updateBookDetails();
                    break;
                case 6:
                    book.deleteBook();
                    break;
                case 7:
                    member.displayAllMembers();
                    break;
                case 8:
                    member.searchMemberByMemberId();
                    break;
                case 9:
                    member.updateMemberDetails();
                    break;
                case 10:
                    addAdminFromConsole();
                    break;
                case 11:
                    removeAdminFromConsole();
                    break;
                case 12:
                    System.out.println("Logging out...");
                    System.out.println("---------------------------------------------\n");
                    Main.enterTheApplication();
                    return;
                case 13:
                    System.out.println("Exiting the application...");
                    System.out.println("---------------------------------------------\n");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option from the menu.\n");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
            sc.nextLine(); // Consume the invalid input
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
        displayAdminMenu(member); // Re-display the menu 
    }
    public void displayMemberMenu(Members member) {
        try {
            System.out.println("\n---------------------------------------------");
            System.out.println("Member Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Search a book by name");
            System.out.println("3. Search a book by unique ID");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Logout");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");
            Integer userInput = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            switch(userInput) {
                case 1:
                    book.displayAllBooks();
                    break;
                case 2:
                    book.searchBookByName();
                    break;
                case 3:
                    book.searchBookByUniqueId();
                    break;
                case 4:
                    member.borrowBook();
                    break;
                case 5:
                    member.returnBook();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    Main.enterTheApplication();
                    return;
                case 7:
                    System.out.println("Exiting the application...");
                    Main.storeData();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option from the menu.\n");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
            sc.nextLine(); // Consume the invalid input
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
        displayMemberMenu(member); // Re-display the menu 
    }
}