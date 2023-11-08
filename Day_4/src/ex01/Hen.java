package ex01;

public class Hen extends Thread {
    private int num = 50;

    public Hen(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < num; ++i) {
                ProducerConsumer.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
