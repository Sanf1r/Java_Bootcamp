package edu.school21.printer.logic;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "--white", required = true, validateWith = Validator.class)
    private String white;

    @Parameter(names = "--black", required = true, validateWith = Validator.class)
    private String black;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
