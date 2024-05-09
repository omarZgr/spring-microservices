package com.application.serviceClient;

import com.application.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory")
     List<InventoryResponse> isInStock(@RequestParam List<String> skuCode)  ;

}
