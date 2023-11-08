package ex01;

import java.util.LinkedList;

public class ProducerConsumer {
    static LinkedList<Integer> list = new LinkedList<>();
    static int capacity = 1;

    public static synchronized void produce() throws InterruptedException {
        while (list.size() == capacity)
            ProducerConsumer.class.wait();
        System.out.println("Egg");
        list.add(capacity);
        ProducerConsumer.class.notify();
    }

    public static synchronized void consume() throws InterruptedException {
        while (list.size() == 0)
            ProducerConsumer.class.wait();
        list.remove();
        System.out.println("Hen");
        ProducerConsumer.class.notify();
    }

}
