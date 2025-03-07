import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentByTransferTest {

    @Test
    void testValidBankTransfer() {
        PaymentByTransfer payment = new PaymentByTransfer("1234", "Bank ABC", "REF123456");
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus(), "Valid bank transfer should result in SUCCESS");
    }
    @Test
    void testEmptyBankName() {
        PaymentByTransfer payment = new PaymentByTransfer("1234", "", "REF123456");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(), "Empty bank name should result in REJECTED");
    }

    @Test
    void testEmptyReferenceCode() {
        PaymentByTransfer payment = new PaymentByTransfer("1234", "Bank ABC", "");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(), "Empty reference code should result in REJECTED");
    }

    @Test
    void testNullBankName() {
        PaymentByTransfer payment = new PaymentByTransfer("1234", null, "REF123456");
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(), "Null bank name should result in REJECTED");
    }

    @Test
    void testNullReferenceCode() {
        PaymentByTransfer payment = new PaymentByTransfer("1234", "Bank ABC", null);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus(), "Null reference code should result in REJECTED");
    }
}
