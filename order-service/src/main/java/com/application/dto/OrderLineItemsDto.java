package com.application.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderLineItemsDto {

    private Long id ;

    private String skuCode ;
    private float price ;
    private int quantity ;
}
