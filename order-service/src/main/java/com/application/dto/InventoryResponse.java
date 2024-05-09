package com.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryResponse {

    private String skuCode ;
    private boolean isInStock ;
}
