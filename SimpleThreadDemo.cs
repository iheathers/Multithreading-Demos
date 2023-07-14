using System;

class Prime {
  public static bool IsPrime(long value) {
    for (long div=2; div<value; div++)
      if (value%div == 0)
        return false;
    return true;
  }
}

public class SimpleThreadDemo {
  public static void Main() {
    var number = 9999999967L;

    //~! This takes a long time, can we run in a thread?
    var result = Prime.IsPrime(number);
    Console.WriteLine(number + " is prime? " + result);
    Console.WriteLine("Program ends");
  }
}