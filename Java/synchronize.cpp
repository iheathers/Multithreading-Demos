#include <iostream>
#include <string>
#include <thread>
#include <chrono>
#include <random>
#include <atomic>

using namespace std;

typedef uniform_int_distribution<mt19937::result_type> generator;

void thread_sleep_between_random(int min_seconds, int max_seconds) {
    min_seconds = min_seconds >= max_seconds ? 0 : min_seconds;

    random_device r_dev;
    mt19937 engine(r_dev());
    generator random_seconds(min_seconds, max_seconds);

    this_thread::sleep_for(chrono::seconds(random_seconds(engine)));
}

class Person {
    int id;
    string first_name;
    string last_name;
    int age;
    string location;

    public:
    Person() { }

    Person(int id, string first_name, string last_name, int age, string location)
        : id(id), first_name(first_name), last_name(last_name), age(age), location(location) { }

    void set_details(int id, string first_name, string last_name, int age, string location) {
        this->id = id;
        cout << "Setting id to: " + to_string(id) + "\n";
        thread_sleep_between_random(0, 2);

        this->first_name = first_name;
        cout << "Setting first_name to: " + first_name + "\n";
        thread_sleep_between_random(0, 2);

        this->last_name = last_name;
        cout << "Setting last_name to: " + last_name + "\n";
        thread_sleep_between_random(0, 2);

        this->age = age;
        cout << "Setting age to: " + to_string(age) + "\n";
        thread_sleep_between_random(0, 2);

        this->location = location;
        cout << "Setting location to: " + location + "\n";
        thread_sleep_between_random(0, 2);
    }

    void print() {
        cout << "\tID: " + to_string(id) + " "
            + "\tNAME: " + first_name + " " + last_name
            + "\tAGE: " + to_string(age) + " "
            + "\tLOCATION: " + location
            + "\n";
    }
};

int main(int argc, char const *argv[]) {

    //~* Single object in heap (will be shared) 
    auto bjarne = new Person();

    //~? Thread 1 setting details of the object
    thread thread_1([&bjarne]() {
        bjarne->set_details(1, "Bjarne", "Stroustrup", 72, "NY US");
        bjarne->print();
    });

    //~! Second reference to the SAME object 
    auto dennis = bjarne;

    //~? Thread 2 simultaneously setting details of the same object
    thread thread_2([&dennis]() {
        dennis->set_details(2, "Dennis", "Ritchie", 70, "NJ US");
        dennis->print();
    });

    //~! Output is all messed up... can you synchronize access?

    //% Wait for threads to complete.
    thread_2.join();
    thread_1.join();

    cout << "Program ends..." << endl;
    return 0;
}
