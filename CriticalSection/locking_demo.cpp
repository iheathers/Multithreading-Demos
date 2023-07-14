#include <iostream>
#include <string>
#include <thread>
#include <chrono>

using namespace std;

void take_turns(string name) {
    name = "Thread " + name + " -> ";
    int turn = 1;
    while (100 >= turn) {
        cout << (name + to_string(turn++) + "\n");
        this_thread::sleep_for(chrono::seconds(1));
    }
    cout << (name + " Completed!\n");
}

int main(int argc, char const *argv[]) {

    thread ping(take_turns, "Chunnu");

    this_thread::sleep_for(chrono::seconds(5));

    thread pong(take_turns, "Munnu");

    ping.join();
    pong.join();

    return 0;
}
