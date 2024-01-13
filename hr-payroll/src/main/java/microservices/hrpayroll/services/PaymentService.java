package microservices.hrpayroll.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservices.hrpayroll.entities.Payment;
import microservices.hrpayroll.entities.Worker;
import microservices.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "getPaymentAlternative")
    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(Objects.requireNonNull(worker).getName(), worker.getDailyIncome(), days);
    }

    public Payment getPaymentAlternative(int days) {
        return  new Payment("Brann", 400.0, days);
    }
}
