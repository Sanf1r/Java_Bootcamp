package ex02;

public class Multi extends Thread {
    private Integer id;
    private Integer[] arr;
    private int from;
    private int to;
    private int result = 0;

    public Multi(int id, Integer[] arr, int from, int to) {
        this.id = id;
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for (int i = from; i < to && i < arr.length; ++i) {
            result += arr[i];
        }
    }

    @Override
    public String toString() {
        return "Thread " + id + ": from " + from + " to " + to + " sum is " + result;
    }
}
