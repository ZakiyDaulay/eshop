import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;


@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void testAddPaymentWithValidVoucher() {
        String id = "1234";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678"); // Valid voucher

        Payment expectedPayment = new Payment(id, PaymentMethod.VOUCHER, PaymentStatus.SUCCESS, paymentData);


        // Mock repository behavior
        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository).save(any(Payment.class));

        // Execute service method
        Payment payment = paymentService.addPayment(id, method, paymentData);

        // Assertions
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus(),
                "Expected PaymentStatus.SUCCESS for a valid voucher code.");
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());

        // Verify interactions
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidVoucher_TooShort() {
        String id = "5678";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234"); // Too short (invalid)

        Payment expectedPayment = new Payment(id, PaymentMethod.VOUCHER, PaymentStatus.REJECTED, paymentData);

        // Mock repository behavior
        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository).save(any(Payment.class));

        // Execute service method
        Payment payment = paymentService.addPayment(id, method, paymentData);

        // Assertions
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Expected PaymentStatus.REJECTED for an invalid voucher code.");
        assertEquals(paymentData, payment.getPaymentData());

        // Verify interactions
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidVoucher_NoESHOPPrefix() {
        String id = "91011";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP1234ABC5678"); // Missing "ESHOP" prefix (invalid)

        // Mock repository behavior
        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository).save(any(Payment.class));

        // Execute service method
        Payment payment = paymentService.addPayment(id, method, paymentData);

        // Assertions
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Expected PaymentStatus.REJECTED for an invalid voucher code.");
        assertEquals(paymentData, payment.getPaymentData());

        // Verify interactions
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidVoucher_NotEnoughNumbers() {
        String id = "121314";
        String method = "Voucher";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCD5678XYZ"); // Only 4 numbers instead of 8 (invalid)

        // Mock repository behavior
        doAnswer(invocation -> invocation.getArgument(0))
                .when(paymentRepository).save(any(Payment.class));

        // Execute service method
        Payment payment = paymentService.addPayment(id, method, paymentData);

        // Assertions
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(),
                "Expected PaymentStatus.REJECTED for an invalid voucher code.");
        assertEquals(paymentData, payment.getPaymentData());

        // Verify interactions
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
}
