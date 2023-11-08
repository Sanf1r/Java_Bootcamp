package ex01;

public class Egg extends Thread {
    private int num = 50;

    public Egg(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < num; ++i) {
                ProducerConsumer.produce();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
