package edu.school21.printer.logic;

import com.beust.jcommander.*;

public class Validator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (value.equals("RED") || value.equals("GREEN") || value.equals("YELLOW") || value.equals("BLUE")
                || value.equals("MAGENTA") || value.equals("CYAN")) {
        } else {
            throw new ParameterException("Parameter " + name + " should be RED, GREEN, YELLOW, BLUE, MAGENTA or CYAN");
        }
    }
}
