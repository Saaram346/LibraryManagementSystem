package Library;

import java.util.ArrayList;

import Library.Constants.MEMBERS;

public class Members extends ConsoleInteraction {
    public String memberName;
    private String password;
    public String memberId;
    public String phoneNumber;
    public String joiningDate;
    public ArrayList<Books> borrowedBooks = new ArrayList<>();
    public ArrayList<Books> reservedBooks = new ArrayList<>();

    public static ArrayList<Members> membersList = new ArrayList<>();
    public static ArrayList<Members> adminList = new ArrayList<>();

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberName() {
        return this.memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return this.memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJoiningDate() {
        return this.joiningDate;
    }
    public void setJoiningDate() {
        this.joiningDate = java.time.LocalDate.now().toString();
    }
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void addBorrowedBook(Books book) {
        this.borrowedBooks.add(book);
    }
    public void removeBorrowedBook(Books book) {
        this.borrowedBooks.remove(book);
    }
    public ArrayList<Books> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void addReservedBook(Books book) {
        this.reservedBooks.add(book);
    }
    public ArrayList<Books> getReservedBooks() {
        return this.reservedBooks;
    }

    public void addMember(String memberName, String phoneNumber) {
        setMemberName(memberName);
        setPhoneNumber(phoneNumber);
        setJoiningDate();
        setMemberId("MEMBER" + System.currentTimeMillis() % 1_000_000_0);
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
        membersList.add(this);
    }

    public void displayMemberDetails(Members member) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Phone Number: " + member.getPhoneNumber());
        System.out.println("Joining Date: " + member.getJoiningDate());
        System.out.println("Borrowed Books: ");
        for(Books book : member.getBorrowedBooks()) {
            System.out.println("- " + book.getBookName() + " by " + book.getAuthorName());
        }
        System.out.println("Reserved Books: ");
        for(Books book : member.getReservedBooks()) {
            System.out.println("- " + book.getBookName() + " by " + book.getAuthorName());
        }
        System.out.println("---------------------------------------------\n");
    }

    public void displayAllMembers() {
        System.out.println("\n---------------------------------------------");
        System.out.println("Members List:");
        for(Members member : membersList) {
            System.out.println("- " + member.getMemberName() + " (ID: " + member.getMemberId() + ")");
            System.out.println();
        }
        System.out.println("---------------------------------------------\n");
    }

    public void updateMemberDetails() {
        System.out.print("Enter the member ID to update details: ");
        String memberId = getMemberIdFromConsole(true, false);
        for(int i = 0; i < membersList.size(); i++) {
            if(membersList.get(i).getMemberId().equals(memberId)) {
                System.out.println("Enter a new member name or press enter to skip: ");
                String updatedMemberName = getMemberNameFromConsole(false, false);
                if(!updatedMemberName.isEmpty()) {
                    membersList.get(i).setMemberName(updatedMemberName);
                    System.out.println("Member Name updated successfully");
                }
                System.out.println("Enter a new phone number or press enter to skip: ");
                String updatedPhoneNumber = getPhoneNumberFromConsole(false, false);
                if(!updatedPhoneNumber.isEmpty()) {
                    membersList.get(i).setPhoneNumber(updatedPhoneNumber);
                    System.out.println("Phone Number updated successfully");
                }
                // System.out.println("Enter a new joining date or press enter to skip: ");
                // String updatedJoiningDate = sc.nextLine();
                // if(!updatedJoiningDate.isEmpty()) {
                //     membersList.get(i).setJoiningDate(updatedJoiningDate);
                //     System.out.println("Joining Date updated successfully");
                // }
                System.out.println("Member details updated successfully with details: ");
                displayMemberDetails(membersList.get(i));
                return;
            }
        }
        System.out.println("Member ID not found.");
    }

    public void deleteMember() {
        System.out.print("Enter the member ID to delete: ");
        String memberId = getMemberIdFromConsole(true, false);
        for(int i = 0; i < membersList.size(); i++) {
            if(membersList.get(i).getMemberId().equals(memberId)) {
                System.out.println("Removing member " + membersList.get(i).getMemberName());
                membersList.remove(i);
                System.out.println("Member deleted successfully...\n");
                return;
            }
        }
        System.out.println("Member ID not found.\n");
    }

    public Members searchMemberByMemberId() {
        System.out.print("Enter the member ID to search: ");
        String memberId = getMemberIdFromConsole(true, false);
        System.out.println("Search results for member ID '" + memberId + "':");
        Boolean found = false;
        System.out.println("\n---------------------------------------------");
        for(Members member : membersList) {
            if(member.getMemberId().equalsIgnoreCase(memberId)) {
                displayMemberDetails(member);
                found = true;
                return member;
            }
        }
        if(!found) {
            System.out.println("No results found.\n");
        }
        System.out.println("---------------------------------------------\n");
        return null;
    }

    public Members getMemberByMemberId(String memberId) {
        for(Members member : membersList) {
            if(member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }

    public Boolean validateDetails(MEMBERS type,String content) {
        for (Members member : membersList) {
            if(type == MEMBERS.MEMBER_NAME && member.getMemberName().equals(content)) {
                return true;
            }
            else if(type == MEMBERS.MEMBER_ID && member.getMemberId().equals(content)) {
                return true;
            }
            else if(type == MEMBERS.PHONE_NUMBER && member.getPhoneNumber().equals(content)) {
                return true;
            }
        }
        return false;
    }

    public void addAdmin(String memberName, String phoneNumber, String password) {
        setMemberName(memberName);
        setPhoneNumber(phoneNumber);
        setJoiningDate();
        setMemberId("ADMIN" + System.currentTimeMillis() % 1_000_000_0);
        setPassword(password);
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
        adminList.add(this);
    }

    public void removeAdmin() {
        System.out.print("Enter the Admin ID to delete: ");
        String adminId = getMemberIdFromConsole(true, false);
        for(int i = 0; i < adminList.size(); i++) {
            if(adminList.get(i).getMemberId().equals(adminId)) {
                System.out.println("Removing Admin " + adminList.get(i).getMemberName());
                adminList.remove(i);
                System.out.println("Admin deleted successfully...\n");
                return;
            }
        }
        System.out.println("Admin ID not found.\n");
    }

    public void borrowBook() {
        String bookId = getUniqueIdOfBookFromConsole();
        for(Books book : Books.booksList) {
            if(book.getUniqueId().equals(bookId)) {
                if(book.getAvailability()) {
                    book.setBorrowedDate(java.time.LocalDate.now().toString());
                    book.setDueDate(java.time.LocalDate.now().plusWeeks(2).toString());
                    addReservedBook(book);
                    addBorrowedBook(book);
                    book.setBorrowedByMembers(this);
                    book.addReservedByMembers(this);
                    book.setStock(book.getStock() - 1);
                    System.out.println("You have successfully borrowed '" + book.getBookName() + "'. Please return it by " + book.getDueDate() + ".");
                }
                else {
                    System.out.println("Sorry, '" + book.getBookName() + "' is currently unavailable.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
        if(ConsoleInteraction.userConfirmation("Do you want to search for another book?")) {
            borrowBook();
        }
    }

    public void returnBook() {
        String bookId = getUniqueIdOfBookFromConsole();
        for(Books book : borrowedBooks) {
            if(book.getUniqueId().equals(bookId)) {
                book.setReturnedDate(java.time.LocalDate.now().toString());
                removeBorrowedBook(book);
                book.setStock(book.getStock() + 1);
                book.removeBorrowedByMembers(this);
                System.out.println("You have successfully returned '" + book.getBookName() + "'. Thank you!");
                return;
            }
        }
        System.out.println("You have not borrowed a book with that ID.");
        if(ConsoleInteraction.userConfirmation("Do you want to search for another book?")) {
            returnBook();
        }
    }
}
