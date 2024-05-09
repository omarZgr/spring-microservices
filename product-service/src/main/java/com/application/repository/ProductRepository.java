package com.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.application.model.Product ;


@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    
}
