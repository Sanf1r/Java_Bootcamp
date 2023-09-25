import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.err.println("Wrong argument!");
            System.exit(-1);
        }
        String pathString = args[0].substring(17);
        System.out.println(pathString);
        Path path = Paths.get(pathString);
        if (!path.isAbsolute()) {
            System.err.println("Not absolute path!");
            System.exit(-1);
        }
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] split = input.split(" ");
        while (!input.equals("exit")) {
            if (split.length == 1 && split[0].equals("ls")) {
                lsMode(pathString);
            } else if (split.length == 2 && split[0].equals("cd")) {
                pathString = cdMode(split, pathString);
            } else if (split.length == 3 && split[0].equals("mv")) {
                mvMode(split, pathString);
            } else {
                System.err.println("Wrong input!");
            }
            input = in.nextLine();
            split = input.split(" ");
        }
        in.close();
    }

    private static long dirSize(File file) throws IOException {
        return Files.walk(file.toPath())
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

    private static void lsMode(String pathString) throws IOException {
        try {
            File obj = new File(pathString);
            if (obj.exists() && obj.isDirectory()) {
                File[] a = obj.listFiles();
                for (File data : a) {
                    if (data.isDirectory()) {
                        System.out.printf("%s %.2f KB%n", data.getName(), (double) dirSize(data) / 1024);
                    } else {
                        System.out.printf("%s %.2f KB%n", data.getName(), (double) data.length() / 1024);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Wrong input!");
        }
    }

    private static String cdMode(String[] split, String pathString) throws IOException {
        try {
            String inputPath = Paths.get(pathString).resolve(split[1]).normalize().toString();
            File check = new File(inputPath);
            if (check.isDirectory()) {
                pathString = inputPath;
                System.out.println(pathString);
            } else {
                System.err.println("Wrong input!");
            }
        } catch (Exception e) {
            System.err.println("Wrong input!");
        }
        return pathString;
    }

    private static void mvMode(String[] split, String pathString) throws IOException {
        try {
            String leftPath = Paths.get(pathString).resolve(split[1]).normalize().toString();
            String rightPath = Paths.get(pathString).resolve(split[2]).normalize().toString();
            File check = new File(leftPath);
            File sCheck = new File(rightPath);
            if (check.isFile() && sCheck.isDirectory()) {
                Path source = Paths.get(leftPath);
                Path newdir = Paths.get(rightPath);
                Files.move(source, newdir.resolve(source.getFileName()));
            } else if (check.isFile()) {
                Path source = Paths.get(pathString).resolve(split[1]).normalize();
                Files.move(source, source.resolveSibling(split[2]));
            }
        } catch (Exception e) {
            System.err.println("Wrong input!");
        }
    }

}
