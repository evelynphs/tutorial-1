package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest{
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");
        this.orders.add(order1);
    }

    // general payment test
    @Test
    void testCreatePaymentSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyOrders(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, null);
        });
    }

    @Test
    void testCreatePaymentEmptyPaymentData(){
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());
        });
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", "HAHAHEHE", paymentData, this.orders.getFirst());
        });
    }

    // voucher related payment test
    @Test
    void testPaymentVoucherSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentInvalidVoucherCodeNot16Char(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP123");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentInvalidVoucherNotStartedWithEshop(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHHH1234ABC5678");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentBankTransferEmptyBankName(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "haha-hihihi-08-90");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.BANK_TRANSFER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentBankTransferEmptyReferenceCode(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "Bank Central Ahaha");
        paymentData.put("referenceCode", "");
        Payment payment = new Payment("ba7f2543-508f-44fb-be3b-2c770d24fd3d", PaymentMethod.BANK_TRANSFER.getValue(), paymentData, this.orders.getFirst());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}