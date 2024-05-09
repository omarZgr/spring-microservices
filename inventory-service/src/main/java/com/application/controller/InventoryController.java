package com.application.controller;

import com.application.dto.InventoryResponse;
import com.application.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService  ;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @SneakyThrows
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.warn("Wait Started");
       // Thread.sleep(1000);
        log.warn("Wait Ended");
        return inventoryService.isInStock(skuCode)   ;

    }
}
