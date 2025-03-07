package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testCreatePaymentWithValidData() {
        String id = "1234";
        PaymentMethod method = PaymentMethod.VOUCHER;
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, PaymentStatus.SUCCESS, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentStatusUpdate() {
        Payment payment = new Payment("1234", PaymentMethod.VOUCHER, PaymentStatus.PENDING, new HashMap<>());
        payment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidData() {
        String id = "5678";
        PaymentMethod method = PaymentMethod.VOUCHER;
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE");

        Payment payment = new Payment(id, method, PaymentStatus.REJECTED, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
}
