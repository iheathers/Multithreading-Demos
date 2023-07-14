import java.text.MessageFormat;
import java.util.Random;

class Person {
    int id;
    String firstName;
    String lastName;
    int age;
    String location;

    public void setDetails(int id, String firstName, String lastName, int age, String location)
            throws InterruptedException {
        Random random = new Random();

        this.id = id;
        System.out.println(MessageFormat.format("Setting id to {0}", id));
        Thread.sleep(random.nextInt(2000));

        this.firstName = firstName;
        System.out.println(MessageFormat.format("Setting firstName to {0}", firstName));
        Thread.sleep(random.nextInt(2000));

        this.lastName = lastName;
        System.out.println(MessageFormat.format("Setting lastName to {0}", lastName));
        Thread.sleep(random.nextInt(2000));

        this.age = age;
        System.out.println(MessageFormat.format("Setting age to {0}", age));
        Thread.sleep(random.nextInt(2000));

        this.location = location;
        System.out.println(MessageFormat.format("Setting location to {0}", location));
        Thread.sleep(random.nextInt(2000));
    }

    public void print() {
        System.out.println(
                MessageFormat.format("ID : {0} Name: {1} Age: {2} Location: {3}", id, firstName + " " + lastName, age,
                        location));
    }
}

public class SynchronizeDemo {
    public static void main(String[] args) {
        // ~* Single object in heap (will be shared)
        Person person = new Person();

        // ~? Thread 1 setting details of the object
        new Thread() {
            public void run() {
                try {
                    person.setDetails(1, "Anders", "Hejlsberg", 59, "Redmond");
                    person.print();
                } catch (Exception e) {
                }
            }
        }.start();

        // ~! Second reference to the SAME object
        Person steve = person;

        // ~? Thread 2 simultaneously setting details of the same object
        new Thread() {
            public void run() {
                try {
                    steve.setDetails(2, "Steve", "Lucco", 62, "SF");
                    steve.print();
                } catch (Exception e) {
                }
            }
        }.start();

        // ~! Output is all messed up... can you synchronize access?
    }
}
