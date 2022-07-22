package com.query.query.service;

import com.query.query.entity.Category;
import com.query.query.entity.Product;
import com.query.query.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository iProductRepository;


    @Override
    public Iterable<Product> findByCate(Long id) {
        return iProductRepository.findByCate(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findByI(id);
    }

    @Override
    public Product save(Product product) {
        iProductRepository.addProduct(product.getName(), product.getCategory().getId());
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        return iProductRepository.findByNameLike(name);
    }

    @Override
    public void delete(Long id) {
        iProductRepository.xoaProduct(id);
    }

    @Override
    public Product update(Product product) {
        return iProductRepository.update(product.getName(), product.getCategory().getId());
    }
}
