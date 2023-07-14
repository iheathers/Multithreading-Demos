// package Java;

class Slow extends Thread {
    public void doWork() {
        for (var i = 0; i < 10_000_000; i++) {
            System.out.print("\r" + i);

        }
    }

    @Override
    public void run() {
        doWork();
        System.out.print("Thread has finished");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        var thread = new Slow();
        thread.start();

        System.out.print("Work completed");
    }
}