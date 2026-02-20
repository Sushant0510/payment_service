package integration.payu.payment_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import integration.payu.payment_service.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findByTxnId(String txnId);

	

}
