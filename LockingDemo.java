class TakeTurn extends Thread {

    static Object latch = new Object();

    public static void runLoop() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " waiting to enter");

        synchronized (latch) {
            System.out.println(Thread.currentThread().getName() + " has entered");

            for (var i = 0; i < 10; i++) {
                // System.out.println(i);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }

        System.out.println(Thread.currentThread().getName() + " has exited");
    }

    @Override
    public void run() {
        try {
            runLoop();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class LockingDemo extends Thread {

    public static void main(String[] args) throws InterruptedException {
        var ping = new TakeTurn();
        var pong = new TakeTurn();

        ping.setName("Person1");
        ping.start();

        Thread.sleep(5000);

        pong.setName("Person2");
        pong.start();

    }

}
