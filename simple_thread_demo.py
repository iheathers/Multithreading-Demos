class Prime:
    @staticmethod
    def is_prime(value: int) -> bool:
        div: int = 2
        while div < value:
            if value % div == 0:
                return False
            div += 1
        return True


number: int = 9999999967

# ~! This takes a long time, can we run in a thread?
result: int = Prime.is_prime(number)
print(f"{number} is prime? {result}")
print("Program ends")
