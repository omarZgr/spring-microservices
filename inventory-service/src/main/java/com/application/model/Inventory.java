package com.application.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String skuCode ;
    private int quantity ;
}
