package com.hospital.payment.controller;

import com.hospital.payment.service.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paypalService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/create")
    public RedirectView createPayment() {
        try {
            String cancelUrl = "https://localhost:8080/payment/cancel";
            String successUrl = "https://localhost:8080/payment/success";

            Payment payment = paypalService.createPayment(
                    10, "USD", "paypal",
                    "sale", "Payment description", cancelUrl, successUrl
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new RedirectView(link.getHref());
                }
            }
        } catch (PayPalRESTException exception) {
            log.error("Error occurred: ", exception);
            return new RedirectView("/payment/failure");  // Redirect to failure page on error
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String successPayment(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("payerId") String payerId)
    {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
        }
        return "paymentSuccess";
    }


    @GetMapping("/cancel")
    public String cancelPayment(){
        return "paymentCancel";
    }

    @GetMapping("/error")
    public String cancelError(){
        return "paymentCancel";
    }
}
