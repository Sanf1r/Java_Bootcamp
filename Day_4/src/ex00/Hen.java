package ex00;

public class Hen extends Thread {
    private int num = 50;

    public Hen(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; ++i) {
            System.out.println("Hen!");
        }
    }
}
