package Library;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Books book = new Books();
    public static Members member = new Members();
    public static ConsoleInteraction consoleInteraction = new ConsoleInteraction(book, member);
    public static String dataFilePath = System.getProperty("user.dir") + "/src/Library/DataStore/";
    public static void main(String[] args) {

        ConsoleInteraction.sc = sc;
        restoreData();
        enterTheApplication();
        storeData();
        System.exit(0);

    }

    public static void enterTheApplication() {
        Members member = login();
        if(Members.adminList.contains(member)) {
            consoleInteraction.displayAdminMenu(member);
        }
        else {
            consoleInteraction.displayMemberMenu(member);
        }
    }

    public static Members login() {
        Members member = null;
        System.out.println("\n---------------------------------------------");
        System.out.println("Welcome to the Library Management System!");
        if(ConsoleInteraction.userConfirmation("Are you an Admin?")) {
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
        else if(ConsoleInteraction.userConfirmation("Already have an account?")) {
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
            member = new Members();
            return member = consoleInteraction.addMemberFromConsole(member);
        }
        if(member == null) {
            System.out.println("Login failed. Please try again.");
            login();
        }
        return member;
    }

    public static void restoreData() {
        StoreData storeData = new StoreData(book, member);
        storeData.restoreBooksData(dataFilePath);
        storeData.restoreMembersData(dataFilePath);
        storeData.restoreBorrowedData(dataFilePath);
        storeData.restoreReservedData(dataFilePath);
        storeData.restoreAdminData(dataFilePath);
        System.out.println("Data restoration complete.");
    }

    public static void storeData() {
        StoreData storeData = new StoreData(book, member);
        storeData.storeBooksData(dataFilePath);
        storeData.storeMembersData(dataFilePath);
        storeData.storeBorrowedData(dataFilePath);
        storeData.storeReservedData(dataFilePath);
        storeData.storeAdminData(dataFilePath);
        System.out.println("Data storage complete.");
    }
}