package com.query.query.controller;

import com.query.query.entity.Category;
import com.query.query.entity.Product;
import com.query.query.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cate")
public class Controller {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Product>>findByCategory(@PathVariable("id") Long id){
        Iterable<Product> products = iProductService.findByCate(id);
        if(!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<Optional<Product>>findById(@PathVariable("id") Long id){
        Optional<Product> product = iProductService.findById(id);
        if(!product.isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }


    @PostMapping("create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreate = iProductService.save(product);
        return new ResponseEntity<>(productCreate, HttpStatus.CREATED);
    }

    @GetMapping("findByName")
    public ResponseEntity<List<Product>> searchName(@RequestParam("name") String name){
        List<Product> products = iProductService.findByName(name);
        if(!products.iterator().hasNext()){
            new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        Optional<Product> product = iProductService.findById(id);
        if(!product.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.delete(id);
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id,@RequestBody Product productEdit) {
        Optional<Product> product = iProductService.findById(id);
        if(!product.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productEdit.setId(product.get().getId());
        productEdit = iProductService.save(productEdit);
        return new ResponseEntity<>(productEdit, HttpStatus.OK);
    }

}
