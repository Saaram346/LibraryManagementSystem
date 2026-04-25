package Library;

import java.util.ArrayList;
import java.util.Scanner;

import Library.Constants.MEMBERS;

public class Members {
    public String memberName;
    private String password;
    public String memberId;
    public String phoneNumber;
    public String joiningDate;
    public ArrayList<Books> borrowedBooks;

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
    private void setMemberId(String memberId) {
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

    public void addMember(String memberName, String phoneNumber, String joiningDate) {
        setMemberName(memberName);
        setPhoneNumber(phoneNumber);
        setJoiningDate(joiningDate);
        setMemberId("MEMBER" + System.currentTimeMillis() % 1_000_000_0);
        membersList.add(this);
    }

    public void displayMemberDetails(Members member) {
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Phone Number: " + member.getPhoneNumber());
        System.out.println("Joining Date: " + member.getJoiningDate());
        System.out.println("Borrowed Books: ");
        for(Books book : member.getBorrowedBooks()) {
            System.out.println("- " + book.getBookName() + " by " + book.getAuthorName());
        }
    }

    public void displayAllMembers() {
        System.out.println("All Members:");
        for(Members member : membersList) {
            System.out.println("- " + member.getMemberName() + " (ID: " + member.getMemberId() + ")");
        }
    }

    public void updateMemberDetails(Scanner sc) {
        System.out.print("Enter the member ID to update details: ");
        String memberId = sc.nextLine();
        for(int i = 0; i < membersList.size(); i++) {
            if(membersList.get(i).getMemberId().equals(memberId)) {
                System.out.println("Enter a new member name or press enter to skip: ");
                String updatedMemberName = sc.nextLine();
                if(!updatedMemberName.isEmpty()) {
                    membersList.get(i).setMemberName(updatedMemberName);
                    System.out.println("Member Name updated successfully");
                }
                System.out.println("Enter a new phone number or press enter to skip: ");
                String updatedPhoneNumber = sc.nextLine();
                if(!updatedPhoneNumber.isEmpty()) {
                    membersList.get(i).setPhoneNumber(updatedPhoneNumber);
                    System.out.println("Phone Number updated successfully");
                }
                System.out.println("Enter a new joining date or press enter to skip: ");
                String updatedJoiningDate = sc.nextLine();
                if(!updatedJoiningDate.isEmpty()) {
                    membersList.get(i).setJoiningDate(updatedJoiningDate);
                    System.out.println("Joining Date updated successfully");
                }
                System.out.println("Member details updated successfully with details: ");
                displayMemberDetails(membersList.get(i));
                return;
            }
        }
        System.out.println("Member ID not found.");
    }

    public void deleteMember(Scanner sc) {
        System.out.print("Enter the member ID to delete: ");
        String memberId = sc.nextLine();
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

    public Members searchMemberByMemberId(Scanner sc) {
        System.out.print("Enter the member ID to search: ");
        String memberId = sc.nextLine();
        System.out.println("Search results for member ID '" + memberId + "':");
        Boolean found = false;
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
}
