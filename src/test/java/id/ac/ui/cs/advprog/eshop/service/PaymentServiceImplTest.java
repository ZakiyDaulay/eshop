package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {
    private PaymentRepository paymentRepository;
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }


    @Test
    void testAddPaymentWithInvalidVoucher() {
        String id = "5678";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID");

        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository)
                .save(any(Payment.class));
        Payment payment = paymentService.addPayment(id, method, paymentData);
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Expected PaymentStatus.REJECTED, but got " + payment.getStatus());

        assertEquals(paymentData, payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }


    @Test
    void testSetPaymentStatus() {
        String id = "1234";
        String method = "Voucher";
        Payment payment = new Payment(id, "Voucher", PaymentStatus.PENDING, new HashMap<>());

        when(paymentRepository.findById(id)).thenReturn(java.util.Optional.of(payment));
        Payment expectedPayment = new Payment(id, method, PaymentStatus.REJECTED, payment.getPaymentData());


        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository)
                .save(any(Payment.class));

        Payment updatedPayment = paymentService.setStatus(id, PaymentStatus.SUCCESS);

        assertEquals(PaymentStatus.SUCCESS, updatedPayment.getStatus());
        verify(paymentRepository, times(1)).findById(id);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }


    @Test
    void testGetPaymentById() {
        String id = "1234";
        Payment payment = new Payment(id, "Voucher", PaymentStatus.SUCCESS, new HashMap<>());

        when(paymentRepository.findById(id)).thenReturn(java.util.Optional.of(payment));

        Payment retrievedPayment = paymentService.getPayment(id);

        assertNotNull(retrievedPayment);
        assertEquals(id, retrievedPayment.getId());
        verify(paymentRepository, times(1)).findById(id);
    }

    @Test
    void testGetPaymentByIdNotFound() {
        String id = "9999";

        when(paymentRepository.findById(id)).thenReturn(java.util.Optional.empty());

        Payment retrievedPayment = paymentService.getPayment(id);

        assertNull(retrievedPayment);
        verify(paymentRepository, times(1)).findById(id);
    }
    @Test
    void testAddPaymentWithValidVoucher() {
        String id = "1234";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123456789012");

        // mock save
        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository).save(any(Payment.class));


        Payment payment = paymentService.addPayment(id, method, paymentData);


        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        System.out.println("Actual Payment Status: " + payment.getStatus());



    }
}

