package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment{
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.method = method;
        this.order = order;
        this.paymentData = paymentData;
        boolean valid = validatePayment();

        if(valid){
            this.setStatus(PaymentStatus.SUCCESS.getValue());
            this.order.setStatus(OrderStatus.SUCCESS.getValue());
        }
        else{
            this.setStatus("REJECTED");
            order.setStatus("FAILED");
        }
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order){
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
        this.status = status;
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        } else{
            throw new IllegalArgumentException();
        }
    }

    private boolean validatePayment(){
        boolean valid = true;

        if(this.paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }

        if(this.order == null){
            throw new IllegalArgumentException();
        }

        // validate for voucher method
        if(method.equals(PaymentMethod.VOUCHER.getValue())){
            String voucherCode = paymentData.get("voucherCode");

            if(voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")){
                int numberCounter = 0;
                for(int i=0 ; i<voucherCode.length() ; i++){
                    if(Character.isDigit(voucherCode.charAt(i))){
                        numberCounter++;
                    }
                }

                valid = numberCounter == 8;
            }

            else{
                valid = false;
            }
        }

        // validate for bank transfer method
        else if(method.equals(PaymentMethod.BANK_TRANSFER.getValue())){
            if(paymentData.get("bankName").isEmpty() ||
                    paymentData.get("referenceCode").isEmpty() ||
                    paymentData.get("bankName") == null ||
                    paymentData.get("referenceCode") == null){
                valid = false;
            }
            else{
                valid = true;
            }
        }

        // throw exception for invalid method
        else{
            throw new IllegalArgumentException();
        }

        return valid;
    }
}