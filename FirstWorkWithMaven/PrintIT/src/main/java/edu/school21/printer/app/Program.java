package edu.school21.printer.app;

import java.io.IOException;

import edu.school21.printer.logic.*;
import com.beust.jcommander.*;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            Args argv = new Args();
            JCommander.newBuilder()
                    .addObject(argv)
                    .build()
                    .parse(args);
            Logic work = new Logic();
            work.read(argv.getWhite(), argv.getBlack());
        } else {
            System.err.println("Wrong argument count!");
            System.exit(-1);
        }
    }
}
