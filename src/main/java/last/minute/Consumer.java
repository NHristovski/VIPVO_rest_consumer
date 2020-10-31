package last.minute;

import last.minute.Worker;

import java.util.ArrayList;
import java.util.List;

import static last.minute.Config.*;

public class Consumer {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < N_THREADS; i++) {
            threads.add(new Thread(new Worker()));
        }

        for (int i = 0; i < N_THREADS; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < N_THREADS; i++) {
            threads.get(i).join();
        }
    }
}
