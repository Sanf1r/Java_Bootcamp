package ex00;

public class Program {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].startsWith("--count=")) {
            try {
                Integer count = Integer.parseInt(args[0].substring(8));
                if (count > 0) {
                    Egg eggThread = new Egg(count);
                    Hen henThread = new Hen(count);

                    eggThread.start();
                    henThread.start();
                    try {
                        eggThread.join();
                        henThread.join();
                    } catch (InterruptedException e) {
                    }
                    for (int i = 0; i < count; ++i) {
                        System.out.println("Human!");
                    }
                } else {
                    System.err.println("Wrong count!");
                    System.exit(-1);
                }
            } catch (Exception e) {
                System.err.println("Wrong count!");
                System.exit(-1);
            }

        } else {
            System.err.println("Wrong argument!");
            System.exit(-1);
        }
    }
}
