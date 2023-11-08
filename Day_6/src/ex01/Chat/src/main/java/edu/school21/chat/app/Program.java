package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        final CustomDataSource dataSource = new CustomDataSource();
        MessagesRepositoryJdbcImpl impl = new MessagesRepositoryJdbcImpl(dataSource);
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            try {
                System.out.println("Enter a message ID");
                input = in.nextLine();
                if (input.equals("q"))
                    break;
                int choice = Integer.parseInt(input);
                System.out.println(impl.findById((long) choice).get());
            } catch (Exception e) {
                System.err.println("Wrong id!");
            }
        }
        in.close();
    }

}