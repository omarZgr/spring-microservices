package com.application.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "OrdersLineItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "OrdersLineItems")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String skuCode ;
    private float price ;
    private int quantity ;
 }
