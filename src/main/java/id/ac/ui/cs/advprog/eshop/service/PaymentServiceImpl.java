package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override

    public Payment addPayment(String id, String method, Map<String, String> paymentData) {
        if (!PaymentMethod.isValidMethod(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }

        PaymentMethod paymentMethod = PaymentMethod.valueOf(method.toUpperCase());
        PaymentStatus status = PaymentStatus.PENDING;

        if (paymentMethod == PaymentMethod.VOUCHER) {
            String voucherCode = paymentData.get("voucherCode");
            if (isValidVoucherCode(voucherCode)) {
                status = PaymentStatus.SUCCESS;
            } else {
                status = PaymentStatus.REJECTED;
            }
        }


        Payment payment = new Payment(id, paymentMethod, status, paymentData);
        paymentRepository.save(payment);
        return payment;
    }



    @Override
    public Payment setStatus(String paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        payment.setStatus(status);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentRepository.paymentStorage.values());
    }

    boolean isValidVoucherCode(String voucherCode) {
        return voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.replaceAll("[^0-9]", "").length() == 8;
    }
}
