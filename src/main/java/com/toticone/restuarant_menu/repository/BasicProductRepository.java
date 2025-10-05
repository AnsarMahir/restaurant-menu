package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.entity.BasicProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicProductRepository extends JpaRepository<BasicProduct,Integer> {
    List<BasicProduct> findByType(int type);
}
