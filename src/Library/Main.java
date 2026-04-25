package Library;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static ConsoleInteraction consoleInteraction = new ConsoleInteraction();
    public static void main(String[] args) {

        for(int i = 0; i < 5; i++) {
            Books book = new Books();
            book.addBook("Book" + (i+1), "Author" + (i+1), "Category" + (i+1), true, 10);
        }

        // ConsoleInteraction consoleInteraction = new ConsoleInteraction();
        // consoleInteraction.addBook(sc);
        // consoleInteraction.addBook(sc);
        Books book = new Books();
        book.displayAllBooks();
        // book.searchBookByName(sc);
        // book.updateBookDetails(sc);

        Members member1 = new Members();
        member1.addMember("Member1", "1234567890", "2024-01-01");
        Members member2 = new Members();
        member2.addMember("Member2", "0987654321", "2024-02-01");

        member1.displayAllMembers();

        Main main = new Main();
        Members member = main.login(sc);


    }
    public Members login(Scanner sc) {
        Members member = null;
        System.out.println("Welcome to the Library Management System!");
        if(userConfirmation(sc, "Already have an account?")) {
            System.out.print("Please enter your Member ID to login: ");
            String memberId = sc.nextLine();
            System.out.print("Please enter your phone number: ");
            String phoneNumber = sc.nextLine();
            for(Members memberList : Members.membersList) {
                if(memberList.getMemberId().equals(memberId) && memberList.getPhoneNumber().equals(phoneNumber)) {
                    System.out.println("Login successful! Welcome, " + memberList.getMemberName() + "!");
                    return member = memberList;
                }
            }
            System.out.println("Invalid Member ID or phone number.");
        } 
        else {
            return member = consoleInteraction.addMember(sc);
        }
        if(member == null) {
            System.out.println("Login failed. Please try again.");
            login(sc);
        }
        return member;
    }
    public static Boolean userConfirmation(Scanner sc, String action) {
        System.out.println(action + " (Y/N)");
        String confirmation = sc.next();
        sc.nextLine(); // Consume the newline character
        return confirmation.equalsIgnoreCase("Y");
    }
}

