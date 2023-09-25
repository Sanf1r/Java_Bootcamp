package edu.school21.spring;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String process(String input) {
        return input.toUpperCase();
    }
}
