import random
from threading import Thread
import time


class Person:
    def __init__(self) -> None:
        self.id: int
        self.first_name: str
        self.last_name: str
        self.age: int
        self.location: str

    def set_details(self, id: int, first_name: str, last_name: str, age: int, location: str) -> None:
        self.id = id
        print(f"Setting id to {self.id}")
        time.sleep(random.randint(0, 2))
        self.first_name = first_name
        print(f"Setting first_name to {self.first_name}")
        time.sleep(random.randint(0, 2))
        self.last_name = last_name
        print(f"Setting last_name to {self.last_name}")
        time.sleep(random.randint(0, 2))
        self.age = age
        print(f"Setting age to {self.age}")
        time.sleep(random.randint(0, 2))
        self.location = location
        print(f"Setting location to {self.location}")
        time.sleep(random.randint(0, 2))

    def print(self) -> None:
        t2: str = "\t\t"
        output = f"\n{t2}ID: {self.id}\n"
        output += f"{t2}Name: {self.first_name} {self.last_name}\n"
        output += f"{t2}Age: {self.age}\n"
        output += f"{t2}Location: {self.location}\n"
        print(output)


# ~* Single object in heap (will be shared)
person: Person = Person()


def p_run():
    person.set_details(1, "Anders", "Hejlsberg", 59, "Redmond")
    person.print()


# ~? Thread 1 setting details of the object
Thread(target=p_run).start()

# ~! Second reference to the SAME object
steve: Person = person


def s_run():
    steve.set_details(2, "Steve", "Lucco", 62, "SF")
    steve.print()


# ~? Thread 2 simultaneously setting details of the same object
Thread(target=s_run).start()

# ~! Output is all messed up... can you synchronize access?
