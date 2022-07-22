package com.query.query.service;

import com.query.query.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Iterable<Product> findByCate(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> findByName(String Name);

    void delete(Long id);

    Product update(Product product);


}
