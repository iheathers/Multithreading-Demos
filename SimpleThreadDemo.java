class Prime implements Runnable {
  public static boolean isPrime(long value) {
    for (long div = 2; div < value; div++) {
      // System.out.print("\r" + div);
      if (value % div == 0)
        return false;
    }
    return true;
  }

  @Override
  public void run() {
    // isPrime(0);
  }
}

public class SimpleThreadDemo {
  public static void main(String[] args) {
    var number = 9999999967L;

    // ~! This takes a long time, can we run in a thread?
    var result = Prime.isPrime(number);
    System.out.println(number + " is prime? " + result);

    var thread = new Thread(new Prime.isPrime(number));

    System.out.println("Program ends");
  }
}