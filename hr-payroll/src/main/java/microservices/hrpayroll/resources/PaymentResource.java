package microservices.hrpayroll.resources;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import microservices.hrpayroll.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.hrpayroll.entities.Payment;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    private final PaymentService service;

    public PaymentResource(PaymentService service) {
        this.service = service;
    }

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        Payment payment = service.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternative(Integer days) {
        Payment payment = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(payment);
    }
}
