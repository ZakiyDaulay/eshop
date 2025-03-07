package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepository {

    public final Map<String, Payment> paymentStorage = new HashMap<>();

    public void save(Payment payment) {
        paymentStorage.put(payment.getId(), payment);
    }

    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(paymentStorage.get(id));
    }

    public void delete(String id) {
        paymentStorage.remove(id);
    }


    public List<Payment> findByMethod(PaymentMethod method) {
        return paymentStorage.values()
                .stream()
                .filter(payment -> payment.getMethod() == method)
                .collect(Collectors.toList());
    }
}
