package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class PaymentByTransfer extends Payment {
    private final String bankName;
    private final String referenceCode;

    public PaymentByTransfer(String id, String bankName, String referenceCode) {
        super(id, PaymentMethod.BANK_TRANSFER, determineStatus(bankName, referenceCode), createPaymentData(bankName, referenceCode));
        this.bankName = bankName;
        this.referenceCode = referenceCode;
    }

    private static PaymentStatus determineStatus(String bankName, String referenceCode) {
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            return PaymentStatus.REJECTED;
        }
        return PaymentStatus.SUCCESS;
    }

    private static Map<String, String> createPaymentData(String bankName, String referenceCode) {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", bankName);
        paymentData.put("referenceCode", referenceCode);
        return paymentData;
    }
}
