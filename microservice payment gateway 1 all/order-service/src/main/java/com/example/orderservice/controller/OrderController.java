package com.example.orderservice.controller;

import com.example.orderservice.common.Payment;
import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/orders")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);
    }

//    public List<Order>

//    http://localhost:9192/h2-console and same on 9191
//    jdbc:h2:mem:testdb for h2 database and we are making a

}
