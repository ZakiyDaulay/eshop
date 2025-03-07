package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    private final String id;
    private final PaymentMethod method;
    @Setter
    private PaymentStatus status;
    private final Map<String, String> paymentData;

    public Payment(String id, PaymentMethod method, PaymentStatus status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
    }

    public String getMethodAsString() {
        return method.getValue(); // Returns the string representation
    }
}
