package com.query.query.repository;

import com.query.query.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM demo.product\n" +
            "            JOIN demo.category on product.category_id = category.id\n" +
            "            WHERE demo.category.id = ?" , nativeQuery = true)
    Iterable<Product>findByCate(Long id);
    @Query(value = "SELECT * FROM demo.product \n" +
            "WHERE demo.product.id = ?1", nativeQuery = true)
    Optional<Product> findByI(Long id);

    @Query(value = "SELECT * FROM demo.product p\n" +
            "where p.name like  CONCAT('%',:name,'%')",nativeQuery = true)
    List<Product> findByNameLike(@Param("name") String name);
    @Modifying
    @Query(value = "insert into demo.product(name, category_id)\n" +
            "value(?1,?2);",nativeQuery = true)
    void addProduct(String name, Long category_id);


    @Modifying
    @Query(value = "delete from demo.product where product.id = ?", nativeQuery = true)
    void xoaProduct(Long id);

    @Modifying
    @Query(value = "UPDATE demo.product\n" +
            "set product.name = :name, category_id = :category_id\n" +
            "where product.id = id",nativeQuery = true)
    Product update(@Param("name") String name, @Param("category_id") Long category_id);
}
