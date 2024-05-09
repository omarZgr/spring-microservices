package com.application.controller;

import com.application.dto.OrderRequest;
import com.application.service.OrderService;
import com.application.service.OrderService_WebClient_Mono;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest)
    {
       // log.warn("Value of  quantity is : "+orderRequest.getOrderLineItemsDtos().get(0).getQuantity() +" ---rv")  ;
        orderService.placeOrder(orderRequest) ;
        return CompletableFuture.supplyAsync(()->  "Order Placed Successfully") ;

    }

    private CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException exception)
    {
        return CompletableFuture.supplyAsync(()->  "Oops ! Something went wrong..." ) ;
    }
}
