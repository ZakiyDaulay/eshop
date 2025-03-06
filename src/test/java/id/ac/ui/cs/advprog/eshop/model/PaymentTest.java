package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    @Test
    void testCreatePaymentWithValidData() {
        String id = "1234";
        String method = "Voucher";
        String status = "SUCCESS";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, status, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
    @Test
    void testPaymentStatusUpdate() {
        Payment payment = new Payment("1234", "Voucher", "PENDING", new HashMap<>());
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentWithInvalidData() {
        String id = "5678";
        String method = "Voucher";
        String status = "REJECTED";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE");

        Payment payment = new Payment(id, method, status, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
}