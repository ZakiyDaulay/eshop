package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1", "Voucher", PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("1");
        assertTrue(retrievedPayment.isPresent());
        assertEquals(payment, retrievedPayment.get());
    }

    @Test
    void testFindByIdWhenPaymentExists() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "VALIDCODE");

        Payment payment = new Payment("2", "Credit Card", PaymentStatus.SUCCESS, paymentData);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("2");
        assertTrue(retrievedPayment.isPresent());
        assertEquals(payment, retrievedPayment.get());
    }

    @Test
    void testFindByIdWhenPaymentDoesNotExist() {
        Optional<Payment> retrievedPayment = paymentRepository.findById("99");
        assertFalse(retrievedPayment.isPresent());
    }

    @Test
    void testDeletePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "TO_DELETE");

        Payment payment = new Payment("3", "Bank Transfer", PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        paymentRepository.delete("3");
        Optional<Payment> retrievedPayment = paymentRepository.findById("3");
        assertFalse(retrievedPayment.isPresent());
    }

    @Test
    void testUpdatePaymentStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "UPDATE_CODE");

        Payment payment = new Payment("4", "E-Wallet", PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("4");
        assertTrue(retrievedPayment.isPresent());
        assertEquals(PaymentStatus.SUCCESS, retrievedPayment.get().getStatus());
    }
}
