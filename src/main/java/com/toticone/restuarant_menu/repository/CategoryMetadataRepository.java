package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.entity.CategoryMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryMetadataRepository extends JpaRepository<CategoryMetadata,Integer> {

    CategoryMetadata findByCategoryName(String category);

    @Query("SELECT c.categoryName FROM CategoryMetadata c")
    List<String> findAllCategoryNames();
}
