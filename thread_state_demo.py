from threading import Thread
import time


class Counter:
    @staticmethod
    def execute() -> None:
        count: int = 0
        while count < 100:
            count += 1
            print(f"\rcount {count}")
            time.sleep(1)

thread: Thread = Thread(target= Counter.execute)
thread.start()

#~* Call is_alive() and check state
print("Program ends")