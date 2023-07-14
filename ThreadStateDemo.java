
class MyThread extends Thread {

    @Override
    public void run() {
        try {
            int count = 0;
            while (count < 100) {
                System.out.println("\rcount " + ++count);
                Thread.sleep(1000);
                System.out.println("State 3: " + Thread.currentThread().getState());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        var thread = new MyThread();

        // thread.sleep(2000);
        System.out.println("State 1: " + thread.getState());

        thread.start();

        System.out.println("State 2: " + thread.getState());

        // Thread.sleep(2000);

        System.out.println("State 4: " + Thread.currentThread().getState());

        // Call sleep, abort, interrupt, resume methods and check state
        System.out.println("Program ends");
    }
}