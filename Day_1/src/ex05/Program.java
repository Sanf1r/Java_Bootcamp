package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] students = new String[10];
        int studCount = 0;
        String one = in.next();
        while (!one.equals(".")) {
            students[studCount++] = one;
            one = in.next();
        }
        String[][] days = new String[31][6];
        days[0][0] = "September 2020";
        int classCount = 0;
        fillDays(days);
        one = in.next();
        while (!one.equals(".")) {
            classCount = insertClass(days, one, in.next(), classCount);
            one = in.next();
        }

        String[][] timeTable = new String[studCount][classCount + 1];
        for (int i = 0; i < studCount; ++i) {
            timeTable[i][0] = students[i];
        }

        one = in.next();
        while (!one.equals(".")) {
            insertAtt(timeTable, one, in.next(), in.next(), in.next(), days);
            one = in.next();
        }

        printDays(days);
        printAtt(timeTable);

        in.close();
    }

    private static void fillDays(String[][] days) {
        int max = 31;
        for (int t = 0; t < max;) {
            for (int y = 1; y < 8; ++y) {
                if (y == 1 && 0 + t + y < max) {
                    days[0 + t + y][0] = "TU";
                } else if (y == 2 && 0 + t + y < max) {
                    days[0 + t + y][0] = "WE";
                } else if (y == 3 && 0 + t + y < max) {
                    days[0 + t + y][0] = "TH";
                } else if (y == 4 && 0 + t + y < max) {
                    days[0 + t + y][0] = "FR";
                } else if (y == 5 && 0 + t + y < max) {
                    days[0 + t + y][0] = "SA";
                } else if (y == 6 && 0 + t + y < max) {
                    days[0 + t + y][0] = "SU";
                } else if (y == 7 && 0 + t + y < max) {
                    days[0 + t + y][0] = "MO";
                }
            }
            t += 7;
        }
    }

    private static int insertClass(String[][] days, String one, String two, int classCount) {
        for (int t = 1; t < 31; ++t) {
            if (days[t][0].equals(two)) {
                days[t][strToInt(one)] = one;
                ++classCount;
            }
        }
        return classCount;
    }

    private static void insertAtt(String[][] timeTable, String one, String two, String three, String four,
            String[][] days) {
        for (int i = 0; i < timeTable.length; ++i) {
            if (one.equals(timeTable[i][0])) {
                String att;
                if (four.equals("HERE")) {
                    att = "1";
                } else {
                    att = "-1";
                }
                timeTable[i][classNumber(days, two, three)] = att;
            }
        }
    }

    private static int classNumber(String[][] days, String time, String date) {
        int res = 0;
        boolean found = false;
        for (int i = 1; i < days.length && !found; ++i) {
            String value = "" + i;
            for (int j = 1; j < days[i].length; ++j) {
                if (days[i][j] != null) {
                    ++res;
                    if (date.equals(value) && time.equals(days[i][j])) {
                        found = true;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private static void printDays(String[][] days) {
        System.out.printf("%10s", "");
        for (int i = 1; i < 31; ++i) {
            for (int j = 1; j < 6; ++j) {
                if (days[i][j] != null) {
                    System.out.printf("%7s%3d|", days[i][j] + ":00 " + days[i][0], i);
                }
            }
        }
        System.out.println();
    }

    private static void printAtt(String[][] timeTable) {
        for (int i = 0; i < timeTable.length; ++i) {
            System.out.printf("%10s", timeTable[i][0]);
            for (int j = 1; j < timeTable[i].length; ++j) {
                if (timeTable[i][j] != null && timeTable[i][j].equals("1")) {
                    System.out.printf("%10s|", timeTable[i][j]);
                } else if (timeTable[i][j] != null && timeTable[i][j].equals("-1")) {
                    System.out.printf("%10s|", timeTable[i][j]);
                } else {
                    System.out.printf("%10s|", "");
                }
            }
            System.out.println();
        }
    }

    private static int strToInt(String input) {
        char[] chars = input.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; ++i) {
            res *= 10;
            res += chars[i] - '0';
        }
        return res;
    }
}
