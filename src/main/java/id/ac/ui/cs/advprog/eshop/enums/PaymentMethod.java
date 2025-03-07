package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("Voucher"),
    COD("COD"),
    BANK_TRANSFER("Bank Transfer");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean isValidMethod(String method) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.getValue().equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}
