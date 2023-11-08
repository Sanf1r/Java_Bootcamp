package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ReadSignatures {
    private static final String SIGNATURES_PATH = "./ex00/signatures.txt";
    // private HashMap<String, String> signaturesMap = new HashMap<>();

    public void load(HashMap<String, String> signaturesMap) throws IOException {

        FileInputStream input = new FileInputStream(SIGNATURES_PATH);
        Scanner readInput = new Scanner(input);
        while (readInput.hasNext()) {
            String out = readInput.nextLine().replaceAll("\\s", "");
            String[] split = out.split(",");
            signaturesMap.put(split[1], split[0]);
        }
        readInput.close();
        input.close();
    }

    public int max(HashMap<String, String> signaturesMap) {
        int max = 0;
        Set<String> set = signaturesMap.keySet();
        for (String str : set) {
            int tmp = str.length();
            if (tmp > max)
                max = tmp;
        }
        return max;
    }
}
