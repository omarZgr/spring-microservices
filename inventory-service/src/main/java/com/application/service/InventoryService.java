package com.application.service;

import com.application.dto.InventoryResponse;
import com.application.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository ;

    public List<InventoryResponse> isInStock(List<String> skuCode)
    {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->

                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }

}
