package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;
import java.util.List;

public interface PaymentService {
    Payment addPayment(String id, String method, Map<String, String> paymentData);
    Payment setStatus(String paymentId, PaymentStatus status);
    Payment getPayment(String paymentId);
    List<Payment> getAllPayments();
}
