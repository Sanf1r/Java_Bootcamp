package edu.school21.spring;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String process(String input) {
        return input.toLowerCase();
    }

}
