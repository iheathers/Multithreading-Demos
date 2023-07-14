#include <iostream>
#include <string>

using namespace std;

class Prime {
    public:
    static bool is_prime(long long number) {
        for (long long div = 2; div < number; div++)
            if (number % div == 0)
                return false;
        return true;
    }
};

string to_string(bool value) {
    return value ? "true" : "false";
}

int main(int argc, char const *argv[]) {
    auto number = 9999999967;
    auto result = Prime::is_prime(number);
    cout << number << " is prime? " << to_string(result) << endl;
    cout << "Program ends" << endl;
    return 0;
}
