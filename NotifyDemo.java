import java.util.Random;
import java.text.MessageFormat;

class Resource {

  private int number;
  private boolean empty = true;
  private boolean evenNumber = false;

  public synchronized int consume(boolean even) throws Exception {
    while (empty || evenNumber != even) {
        System.out.println(MessageFormat.format("{0} is waiting", even ? "Even" : "Odd"));
        wait();
    }
    System.out.format("%s took %d.%n", even ? "Even" : "Odd", number);
    empty = true;
    notify();

    return number;
  }

  public synchronized void produce(int number) throws Exception {
    while (!empty) {
      System.out.println("Producer is waiting ...");
      wait();

    }
    this.number = number;
    evenNumber = number % 2 == 0;
    System.out.println(MessageFormat.format("Producer produced {0}", number));
    empty = false;
    notify();
  }
}

class Producer implements Runnable {

  private Resource resource;

  public Producer(Resource resource) {
    this.resource = resource;
  }

  public void run() {
    Random random = new Random();
    for (var i=0; i<10; i++) {
      int number = random.nextInt(10);
      try {
        Thread.sleep(random.nextInt(100));
        resource.produce(number);
      } catch (Exception e) {
      }
    }
  }
}

class Consumer implements Runnable {

  private final Resource resource;
  private final boolean even;

  public Consumer(boolean even, Resource resource) {
    this.even = even;
    this.resource = resource;
  }

  public void run() {
    Random random = new Random();
    while (true) {
      try {
        resource.consume(even);
        Thread.sleep(random.nextInt(100));
      } catch (Exception e) {
      }
    }
  }
}

public class NotifyDemo {
  private static boolean Even = true;
  private static boolean Odd = false;

  public static void main(String[] args) {
        Resource resource = new Resource();
        (new Thread(new Consumer(Even, resource))).start();
        (new Thread(new Consumer(Odd, resource))).start();
        (new Thread(new Producer(resource))).start();
    }
}
