package org.information.temple.controller;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.information.temple.model.TempleTrackerDonator;
import org.information.temple.service.TempleTrackerDonationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temple-donation")
public class TempleTrackerDonationController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    private final TempleTrackerDonationService donationService;

    public TempleTrackerDonationController(TempleTrackerDonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
                                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer()
                    .getObject().orElse(null);
            if (session != null) {
                TempleTrackerDonator donation = new TempleTrackerDonator();
                donation.setUsername(session.getCustomerDetails().getName());
                donation.setPhoneNumber(session.getCustomerDetails().getPhone());
                donation.setAmount(session.getAmountTotal() / 100.0);
                donation.setPaymentId(session.getId());
                donation.setPaymentMethod("Stripe");
                donation.setPaymentStatus("Completed");

                donationService.saveDonation(donation);
            }
        }

        return ResponseEntity.ok("Received");
    }
}
