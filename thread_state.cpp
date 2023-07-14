#include <iostream>
#include <string>
#include <thread>
#include <chrono>

using namespace std;

void execute_counter(int thread_num) {
    auto count = 0;
    while (100 > count) {
        cout << "\rThread #" << thread_num << " -> Count: " << ++count;
        this_thread::sleep_for(chrono::seconds(1));
    }
}

int main(int argc, char const *argv[]) {
    thread thread_1(execute_counter, 1);
    thread thread_2(execute_counter, 2);

    //~* Call thread_1.detach() and check joinable state for both with and without detach.
        
    cout << "Program ends" << endl;

    return 0;
}
