package com.application.service;

import com.application.dto.InventoryResponse;
import com.application.dto.OrderLineItemsDto;
import com.application.dto.OrderRequest;
import com.application.model.Order;
import com.application.model.OrderLineItems;
import com.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService_WebClient_Mono {

    private final OrderRepository orderRepository  ;
    private final WebClient webClient  ;

    public void placeOrder( OrderRequest orderRequest)
    {
        Order order =new Order() ;

        String uuid = UUID.randomUUID().toString();
        order.setOrderNumber(uuid);


        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList()   ;

        order.setOrderLineItems(orderLineItems);



        List<String> skuCodes =  order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList() ;




        //We want call the inventory-serice used WebClient ----

        InventoryResponse inventoryResponse[] =  webClient.get()
                .uri("http://localhsot:8082/api/inventory",uriBuilder ->
                        uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();

        log.warn("-/----------- just now am here (Start)");


        boolean allProductsInStock = Arrays.stream(inventoryResponse).allMatch(InventoryResponse::isInStock)  ;

        if (allProductsInStock)
            orderRepository.save(order) ;
        else
            throw new   IllegalArgumentException("Product is not in Stock , please try again later")  ;


        log.warn("-/----------- just now am here (END)");



    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems() ;


        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return  orderLineItems ;
    }

}
