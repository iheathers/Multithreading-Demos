import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SingleInstance {
    public String message = "Welcome to App";

    public static void main(String[] args) throws InterruptedException, IOException {

        var file = new File("./", "process.lock");
        try (var randomAccessFile = new RandomAccessFile(file, "rw")) {
            var fileChannel = randomAccessFile.getChannel();
            var isLocked = fileChannel.lock();
            if (isLocked != null) {
                // System.out.println("Waiting");
                var si = new SingleInstance();

                System.out.println(si.message);
                System.out.println("Thread has started");
                Thread.sleep(10000);
                System.out.println("Complete");
            } else {
                System.out.println("Process is already running.");

            }
        }

    }
}
