package com.application.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id ;

    private String orderNumber ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems ;
}
