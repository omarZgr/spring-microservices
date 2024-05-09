package com.application.service;


import com.application.dto.ProductRequest;
import com.application.dto.ProductResponse;
import com.application.model.Product;
import com.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final ProductRepository productRepository ;

    public void createProduct(ProductRequest productRequest)
    {
        Product product  = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build() ;

        productRepository.save(product) ;

        log.warn("Product {} is saved ",product.getId());
    }

    public List<ProductResponse> getAllProducts()
    {
        List<Product> products = productRepository.findAll()  ;

      return   products.stream().map(this::mapToProductResponse).toList()  ;
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build() ;
    }
}
