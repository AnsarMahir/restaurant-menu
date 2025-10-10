package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.entity.BasicProduct;
import com.toticone.restuarant_menu.enums.ExtraTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BasicProductRepository extends JpaRepository<BasicProduct, Integer> {
    List<BasicProduct> findByType(int type);

    @Query("SELECT p FROM BasicProduct p WHERE p.name LIKE %:name%")
    List<BasicProduct> findByNameContaining(@Param("name") String name);

    @Query("SELECT p FROM BasicProduct p JOIN p.extras e WHERE e.type = :extraType")
    List<BasicProduct> findByExtraType(@Param("extraType") ExtraTypes extraType);

    List<BasicProduct> findByCategory(String category);
}
