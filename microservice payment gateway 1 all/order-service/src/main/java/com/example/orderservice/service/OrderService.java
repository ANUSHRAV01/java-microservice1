package com.example.orderservice.service;

import com.example.orderservice.common.Payment;
import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.orderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class OrderService {

    @Autowired
    private orderRepository repository;

    @Autowired
    private RestTemplate template;
//    private Logger log= LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest request){
        String response=" ";
        Order order =request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
//this makes it as a json response
//        log.info("OrderService request :{}",new ObjectMapper().writeValueAsString(request));

        //rest call
        Payment paymentResponse =template.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment,Payment.class);

        response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order is placed":"there is a failure in payment api";

        repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);


    }


}
