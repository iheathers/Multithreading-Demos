public class WaitNotifyDemo {
    int resource;
    int count;

    synchronized public void produce() throws InterruptedException {

        resource = 1;

        if (resource != 0) {
            notify();
        }

        wait();

        System.out.println("Producer resumed");

    }

    synchronized public void consume() throws InterruptedException {

        if (resource == 0) {
            wait();
        }

        System.out.println("Resource: " + resource);
        notify();
        System.out.println("Consumer continued");
    }

    public static void main(String[] args) throws InterruptedException {
        var obj = new WaitNotifyDemo();

        new Thread(() -> {
            try {
                obj.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            try {
                obj.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}