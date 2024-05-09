package com.application.dto;

import com.application.model.OrderLineItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtos ;
}
