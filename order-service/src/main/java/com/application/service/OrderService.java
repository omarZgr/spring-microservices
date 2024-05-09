package com.application.service;


import com.application.dto.InventoryResponse;
import com.application.dto.OrderLineItemsDto;
import com.application.dto.OrderRequest;
import com.application.model.Order;
import com.application.model.OrderLineItems;
import com.application.repository.OrderRepository;
import com.application.serviceClient.InventoryServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository  ;
    private final InventoryServiceClient inventoryServiceClient ;

    @Autowired
    private ObjectMapper objectMapper; // Autowire ObjectMapper to convert objects to JSON


    public void placeOrder( OrderRequest orderRequest)
    {
        Order order =new Order() ;

        String uuid = UUID.randomUUID().toString();
        order.setOrderNumber(uuid);


        log.warn("-/---------- Am Before calling");

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList()   ;

        for (OrderLineItems items:orderLineItems)
            log.warn("Value of orderLineItems >>> "+items.toString()+"  /// ");
        log.warn("-/---------- Am After calling");


        order.setOrderLineItems(orderLineItems);



        List<String> skuCodes =  order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList() ;



        List<InventoryResponse> inventoryResponses = inventoryServiceClient.isInStock(skuCodes) ;

        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(inventoryResponses);
        } catch (Exception e) {
            log.error("Error converting inventory response to JSON", e);
            jsonResponse = "[]"; // Return empty JSON array in case of error
        }

        // Log a warning message
        log.warn("Inventory check for SKUs {} returned: {}", skuCodes, jsonResponse);



        boolean allProductsInStock = true ;

        for (InventoryResponse result:inventoryResponses)
        {
            if (result.isInStock() == false)
                allProductsInStock = false ;

        }

        if (allProductsInStock)
            orderRepository.save(order) ;
        else
            throw new   IllegalArgumentException("Product is not in Stock , please try again later")  ;









    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems() ;


        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return  orderLineItems ;
    }

}
