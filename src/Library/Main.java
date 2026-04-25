package Library;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static ConsoleInteraction consoleInteraction = new ConsoleInteraction();
    public static void main(String[] args) {

        enterTheApplication();

    }

    public static void enterTheApplication() {
        Members member = login(sc);
        if(Members.adminList.contains(member)) {
            consoleInteraction.displayAdminMenu(sc);
        }
        else {
            consoleInteraction.displayMemberMenu(sc);
        }
    }

    public static Members login(Scanner sc) {
        Members member = null;
        System.out.println("Welcome to the Library Management System!");
        if(userConfirmation(sc, "Are you an Admin?")) {
            System.out.print("Please enter your Admin ID to login: ");
            String adminId = sc.nextLine();
            System.out.print("Please enter your Admin password: ");
            String adminPassword = sc.nextLine();
            for(Members admin : Members.adminList) {
                if(admin.getMemberId().equals(adminId) && admin.getPassword().equals(adminPassword)) {
                    System.out.println("Admin login successful! Welcome, Admin!");
                    return member = admin;
                }
            }
        }
        else if(userConfirmation(sc, "Already have an account?")) {
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

