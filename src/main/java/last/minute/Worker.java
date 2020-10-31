package last.minute;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable {
    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        List<Long> responseTimes = new ArrayList<>();

        System.out.println("Worker " + Thread.currentThread().getId() + " started");

        for (int i = 0; i < Config.N_REQUESTS; i++) {

            long before = System.currentTimeMillis();

            restTemplate.getForEntity("http://18.156.137.142:8080/add?num1=10&num2=70", Long.class);

            long after = System.currentTimeMillis();

            long responseTime = after - before;

            responseTimes.add(responseTime);
        }

        System.out.println("Worker " + Thread.currentThread().getId() + " Average: " + responseTimes
                .stream()
                .mapToInt(Long::intValue)
                .average()
                .getAsDouble() + "ms");
    }
}
