package Library;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class StoreData {

    public Members member;
    public Books book;

    public StoreData() {}

    public StoreData(Books book, Members member) {
        this.book = book;
        this.member = member;
    }
    
    public void storeBooksData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "booksData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Books data file.");
                return;
            }
            String bookData = "Book Name,Author Name,Unique ID,Category,Stock\n";
            for(Books book : Books.booksList) {
                bookData += book.getBookName() + "," + book.getAuthorName() + "," + book.getUniqueId() + "," + book.getCategory() + "," + book.getStock() + "\n";
            }
            writeDataInFile(file, bookData);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeMembersData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "membersData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Members data file.");
                return;
            }
            String memberData = "Member Name,Member ID,Phone Number,Joining Date\n";
            for(Members member : Members.membersList) {
                memberData += member.getMemberName() + "," + member.getMemberId() + "," + member.getPhoneNumber() + "," + member.getJoiningDate() + "\n";
            }
            writeDataInFile(file, memberData);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeAdminData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "adminData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Admin data file.");
                return;
            }
            
            String adminData = "Admin Name,Admin ID,Password,Phone Number,Joining Date\n";
            for(Members admin : Members.adminList) {
                adminData += admin.getMemberName() + "," + admin.getMemberId() + "," + admin.getPassword() + "," + admin.getPhoneNumber() + "," + admin.getJoiningDate() + "\n";
            }
            writeDataInFile(file, adminData);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeBorrowedData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "borrowedData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Borrowed data file.");
                return;
            }

            String borrowedData = "Member ID,Book ID,Borrow Date,Return Date,Due Date\n";
            for(Members member : Members.membersList) {
                for(Books book : member.getBorrowedBooks()) {
                    borrowedData += member.getMemberId() + "," + book.getUniqueId() + "," + book.getBorrowedDate() +"," + book.getReturnedDate() + "," + book.getDueDate() + "\n";
                }
            }
            writeDataInFile(file, borrowedData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeReservedData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "reservedData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Reserved data file.");
                return;
            }

            String reservedData = "Member ID,Book ID,Borrow Date,Return Date,Due Date\n";
            for(Members member : Members.membersList) {
                for(Books book : member.getReservedBooks()) {
                    reservedData += member.getMemberId() + "," + book.getUniqueId() + "," + book.getBorrowedDate() +"," + book.getReturnedDate() + "," + book.getDueDate() + "\n";
                }
            }
            writeDataInFile(file, reservedData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreBooksData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "booksData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Books data file.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Book Name")) continue; // Skip header line
                String[] bookDetails = line.split(",");
                if(bookDetails.length == 5) {
                    Books book = new Books();
                    book.setBookName(bookDetails[0]);
                    book.setAuthorName(bookDetails[1]);
                    book.setUniqueId(bookDetails[2]);
                    book.setCategory(bookDetails[3]);
                    book.setStock(Integer.parseInt(bookDetails[4]));
                    Books.booksList.add(book);
                }
                else {
                    System.out.println("Invalid book data format: " + line);
                }
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreMembersData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "membersData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Members data file.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Member Name")) continue; // Skip header line
                String[] memberDetails = line.split(",");
                if(memberDetails.length == 4) {
                    Members member = new Members();
                    member.setMemberName(memberDetails[0]);
                    member.setMemberId(memberDetails[1]);
                    member.setPhoneNumber(memberDetails[2]);
                    member.setJoiningDate(memberDetails[3]);
                    Members.membersList.add(member);
                }
                else {
                    System.out.println("Invalid member data format: " + line);
                }
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreAdminData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "adminData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Admin data file.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Admin Name")) continue; // Skip header line
                String[] adminDetails = line.split(",");
                if(adminDetails.length == 5) {
                    Members admin = new Members();
                    admin.setMemberName(adminDetails[0]);
                    admin.setMemberId(adminDetails[1]);
                    admin.setPassword(adminDetails[2]);
                    admin.setPhoneNumber(adminDetails[3]);
                    admin.setJoiningDate(adminDetails[4]);
                    Members.adminList.add(admin);
                }
                else {
                    System.out.println("Invalid admin data format: " + line);
                }
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreBorrowedData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "borrowedData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Borrowed data file.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Member ID")) continue; // Skip header line
                String[] borrowedDetails = line.split(",");
                if(borrowedDetails.length == 5) {
                    String memberId = borrowedDetails[0];
                    String bookUniqueId = borrowedDetails[1];
                    String borrowDate = borrowedDetails[2];
                    String returnDate = borrowedDetails[3];
                    String dueDate = borrowedDetails[4];

                    Members member = this.member.getMemberByMemberId(memberId);
                    Books book = this.book.getBookByUniqueId(bookUniqueId);
                    if(member != null && book != null) {
                        book.setBorrowedDate(borrowDate);
                        book.setReturnedDate(returnDate);
                        book.setDueDate(dueDate);
                        member.addBorrowedBook(book);
                        book.setBorrowedByMembers(member);
                    }
                }
                else {
                    System.out.println("Invalid borrowed data format: " + line);
                }
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoreReservedData(String dataFilePath) {
        try {
            File file = verifyFileExistence(dataFilePath, "reservedData.csv");
            if(file == null) {
                System.out.println("Failed to access or create Reserved data file.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Member ID")) continue; // Skip header line
                String[] reservedDetails = line.split(",");
                if(reservedDetails.length == 5) {
                    String memberId = reservedDetails[0];
                    String bookUniqueId = reservedDetails[1];
                    String borrowDate = reservedDetails[2];
                    String returnDate = reservedDetails[3];
                    String dueDate = reservedDetails[4];

                    Members member = this.member.getMemberByMemberId(memberId);
                    Books book = this.book.getBookByUniqueId(bookUniqueId);
                    if(member != null && book != null) {
                        book.setBorrowedDate(borrowDate);
                        book.setReturnedDate(returnDate);
                        book.setDueDate(dueDate);
                        member.addReservedBook(book);
                        book.addReservedByMembers(member);
                    }
                }
                else {
                    System.out.println("Invalid reserved data format: " + line);
                }
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File verifyFileExistence(String dataFilePath, String fileName) {
        try {
            File file = new File(dataFilePath + fileName);
            if(!file.exists()) {
                System.out.println(fileName + " not found.");
                if(file.createNewFile()) {
                    System.out.println("New " + fileName + " created successfully.");
                } 
                else {
                    System.out.println("Failed to create new " + fileName + ".");
                }
            }
            return file;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeDataInFile(File file, String data) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(data.getBytes());
            bos.flush();
            bos.close();
            fos.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}