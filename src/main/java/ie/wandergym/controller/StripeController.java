package ie.wandergym.controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import ie.wandergym.domain.entity.CheckoutPayment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAnyRole('SYSADMIN', 'USER')")
public class StripeController {
    private static void init(){
        Stripe.apiKey = "sk_test_51OQdjgB4f2ZoQ9Fd6Zc2cusxuuqkXXAQu3PNIFflJ3wvBTg6S9RR0gF6eSDSVWlSuiML3UxDFjPPouAilsG33gtw00EwnJ2cNG";
    }

    // create a Gson object
    private static Gson gson = new Gson();

    @PostMapping("/subscription")
    /**
     * Used to create a subscription with strpe checkout page
     * @param checkout
     * @return the subscription id
     * @throws StripeException
     */
    public String subscriptionWithCheckoutPage(@RequestBody CheckoutPayment checkout) throws StripeException {
        init();

        SessionCreateParams params = new SessionCreateParams.Builder().setSuccessUrl(checkout.getSuccessUrl())
                .setCancelUrl(checkout.getCancelUrl()).addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION).addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L).setPrice(checkout.getPriceId()).build())
                .build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            return gson.toJson(responseData);
        } catch (Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            return gson.toJson(responseData);
        }
    }
}
