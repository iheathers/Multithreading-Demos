class Counter
{
  public static void execute()
  {
    var count = 0;
    while (count < 100)
    {
      Console.Write("\rcount " + ++count);
      Thread.Sleep(1000);

    }
  }
}

class ThreadStateDemo
{
  public static void Main()
  {
    var thread = new Thread(Counter.execute);

  

    thread.Start();

    //~* Call Sleep, Abort, Interrupt, Resume methods and check state
    Console.WriteLine("Program ends");
  }
}