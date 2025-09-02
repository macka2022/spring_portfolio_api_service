/*package com.example.demoportflio.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest request) {
        String apiUrl = "https://api.test.bictorys.com/pay/v1/charges?payment_type=" + request.getPaymentType();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Api-Key", "public-xxxxxxxxx"); // remplace avec ta clé

        Map<String, Object> body = new HashMap<>();
        body.put("amount", request.getAmount());
        body.put("currency", "XOF");
        body.put("country", "SN");
        body.put("successRedirectUrl", "https://example.com/success");
        body.put("ErrorRedirectUrl", "https://example.com/error");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
*/