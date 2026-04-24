package Library;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ConsoleInteraction consoleInteraction = new ConsoleInteraction();
        consoleInteraction.addBook(sc);
        // Books book = new Books();
        // System.out.println(book.bookName);

    }
}
