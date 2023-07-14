class Person {
  int? id;
  String? firstName;
  String? lastName;
  int? age;
  String? location;

  public void setDetails(int id, String firstName, string lastName, int age, String location) {
    var random = new Random();

    this.id = id;
    Console.WriteLine($"Setting id to {id}");
    Thread.Sleep(random.Next(0, 2000));
    this.firstName = firstName;
    Console.WriteLine($"Setting firstName to {firstName}");
    Thread.Sleep(random.Next(0, 2000));
    this.lastName = lastName;
    Console.WriteLine($"Setting lastName to {lastName}");
    Thread.Sleep(random.Next(0, 2000));
    this.age = age;
    Console.WriteLine($"Setting age to {age}");
    Thread.Sleep(random.Next(0, 2000));
    this.location = location;
    Console.WriteLine($"Setting location to {location}");
    Thread.Sleep(random.Next(0, 2000));
  }

  public void print() {
    Console.WriteLine(
      $@"
        ID: {id}
        Name: {firstName+ " " + lastName}
        Age: {age}
        Location: {location}
      "
    );
  }
}

class SynchronizeDemo {
  public static void Main() {

    //~* Single object in heap (will be shared) 
    var person = new Person();

    //~? Thread 1 setting details of the object 
    new Thread(()=>{
      person.setDetails(1, "Anders", "Hejlsberg", 59, "Redmond");
      person.print();
    }).Start();

    //~! Second reference to the SAME object 

    var steve = person;

    //~? Thread 2 simultaneously setting details of the same object 
    new Thread(()=>{
      steve.setDetails(2, "Steve", "Lucco", 62, "SF");
      steve.print();
    }).Start();

    //~! Output is all messed up... can you synchronize access?
  }
}