package ie.wandergym.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutPayment {
    private String priceId;
    private String successUrl;
    private String cancelUrl;

    public CheckoutPayment() {
        super();
    }

}
