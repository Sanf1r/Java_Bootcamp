package edu.school21.printer.app;

import java.io.IOException;

import edu.school21.printer.logic.*;

public class Program {

    public static boolean paramCheck(String[] args) {
        if (args.length == 3 && args[0].length() == 1 && args[1].length() == 1) {
            return true;
        } else {
            System.err.println("Wrong arguments!");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        if (paramCheck(args)) {
            Logic.read(args);
        }
    }
}
