package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoucherPaymentTest {

@Test
    void testValidVoucherCode() {
        VoucherPayment payment = new VoucherPayment("1234", "ESHOP1234ABC5678");
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus(),
                "Valid voucher should result in SUCCESS");
    }

    @Test
    void testInvalidVoucherCodeLength() {
        VoucherPayment payment = new VoucherPayment("1234", "ESHOP1234ABCD");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Voucher with wrong length should be REJECTED");
    }

    @Test
    void testVoucherWithoutESHOPPrefix() {
        VoucherPayment payment = new VoucherPayment("1234", "SHOP12345678AB");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Voucher without 'ESHOP' should be REJECTED");
    }

    @Test
    void testVoucherWithout8Numbers() {
        VoucherPayment payment = new VoucherPayment("1234", "ESHOPABCDABCDAB");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Voucher without 8 numbers should be REJECTED");
    }
}
