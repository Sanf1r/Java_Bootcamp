package ex03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].startsWith("--threadsCount=")) {
            try {
                Integer count = Integer.parseInt(args[0].substring(15));
                if (count > 0) {
                    String read;
                    String[] split;
                    String[] insert = new String[3];
                    new File(System.getProperty("user.dir") + "/ex03/files").mkdirs();
                    Scanner in = new Scanner(new File("./ex03/files_urls.txt"));
                    ArrayList<String[]> list = new ArrayList<>();

                    while (in.hasNextLine()) {
                        read = in.nextLine();
                        split = read.split(" ");
                        insert[0] = split[0];
                        insert[1] = split[1];
                        list.add(insert.clone());
                    }

                    Multi[] thArr = new Multi[count];

                    for (int i = 0; i < count; ++i) {
                        thArr[i] = new Multi(i + 1, list);
                    }

                    for (Multi thread : thArr) {
                        thread.start();
                    }

                    for (Multi thread : thArr) {
                        thread.join();
                    }

                } else {
                    System.err.println("Wrong input data!");
                    System.exit(-1);
                }
            } catch (Exception e) {
                System.err.println("Wrong input data!");
                System.exit(-1);
            }
        } else {
            System.err.println("Wrong input data!");
            System.exit(-1);
        }
    }
}
