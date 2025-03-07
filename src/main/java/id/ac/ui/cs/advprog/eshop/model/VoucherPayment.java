package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class VoucherPayment extends Payment {
    private final String voucherCode;

    public VoucherPayment(String id, String voucherCode) {
        super(id, PaymentMethod.VOUCHER, determineStatus(voucherCode), createPaymentData(voucherCode));
        this.voucherCode = voucherCode;
    }

    private static PaymentStatus determineStatus(String code) {
        return isValidVoucherCode(code) ? PaymentStatus.SUCCESS : PaymentStatus.REJECTED;
    }

    private static boolean isValidVoucherCode(String code) {
        if (code.length() != 16) return false;
        if (!code.startsWith("ESHOP")) return false;
        String extractedDigits = code.replaceAll("[^0-9]", "");
        return extractedDigits.length() == 8;
    }



    private static Map<String, String> createPaymentData(String voucherCode) {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", voucherCode);
        return data;
    }
}
