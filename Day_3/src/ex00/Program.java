package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.charset.Charset;

public class Program {
    public static void main(String args[]) throws IOException {
        HexReader hexReader = new HexReader();
        ReadSignatures sigReader = new ReadSignatures();
        HashMap<String, String> signaturesMap = new HashMap<>();
        sigReader.load(signaturesMap);
        int max = sigReader.max(signaturesMap);
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        while (!file.equals("42")) {
            boolean flag = false;
            try {
                FileInputStream input = new FileInputStream(file);
                byte[] bytes = new byte[max / 2];
                input.read(bytes);
                String res = hexReader.bytesToHex(bytes);
                for (HashMap.Entry<String, String> pair : signaturesMap.entrySet()) {
                    String tmp = res.substring(0, pair.getKey().length());
                    if (tmp.equals(pair.getKey())) {
                        flag = true;
                        System.out.println("PROCESSED");
                        FileOutputStream fos = new FileOutputStream("./ex00/result.txt", true);
                        fos.write(pair.getValue().getBytes(Charset.forName("UTF-8")));
                        fos.write('\n');
                        fos.close();
                    }
                }
                if (!flag)
                    System.out.println("UNDEFINED");
                input.close();
            } catch (IOException e) {
                System.out.println("Wrong file path!");
            }
            file = in.nextLine();
        }
        in.close();
    }
}
