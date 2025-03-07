package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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

        Payment payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("1");
        assertTrue(retrievedPayment.isPresent());
        assertEquals(payment, retrievedPayment.get());
    }

    @Test
    void testFindByIdWhenPaymentExists() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "VALIDCODE");

        Payment payment = new Payment("2", PaymentMethod.COD, PaymentStatus.SUCCESS, paymentData);
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

        Payment payment = new Payment("3", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        paymentRepository.delete("3");
        Optional<Payment> retrievedPayment = paymentRepository.findById("3");
        assertFalse(retrievedPayment.isPresent());
    }

    @Test
    void testUpdatePaymentStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "UPDATE_CODE");

        Payment payment = new Payment("4", PaymentMethod.COD, PaymentStatus.PENDING, paymentData);
        paymentRepository.save(payment);

        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("4");
        assertTrue(retrievedPayment.isPresent());
        assertEquals(PaymentStatus.SUCCESS, retrievedPayment.get().getStatus());
    }

    @Test
    void testFindByMethod() {
        // Add multiple payments with different methods
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "VOUCHER123");
        Payment payment1 = new Payment("5", PaymentMethod.VOUCHER, PaymentStatus.SUCCESS, paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("orderId", "CODORDER456");
        Payment payment2 = new Payment("6", PaymentMethod.COD, PaymentStatus.PENDING, paymentData2);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        // Retrieve payments by method
        List<Payment> voucherPayments = paymentRepository.findByMethod(PaymentMethod.VOUCHER);
        List<Payment> codPayments = paymentRepository.findByMethod(PaymentMethod.COD);

        // Assertions
        assertEquals(1, voucherPayments.size());
        assertEquals(payment1, voucherPayments.get(0));

        assertEquals(1, codPayments.size());
        assertEquals(payment2, codPayments.get(0));
    }
}
