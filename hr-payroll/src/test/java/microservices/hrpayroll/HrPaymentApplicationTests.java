package microservices.hrpayroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import microservices.hrpayroll.services.PaymentService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class HrPaymentApplicationTests {
	@Autowired
	private PaymentService paymentService;

	@Test
	void contextLoads() {
		assertThat(paymentService).isNotNull();
	}

}
