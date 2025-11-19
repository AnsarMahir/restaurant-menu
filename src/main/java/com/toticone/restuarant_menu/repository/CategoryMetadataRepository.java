package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.entity.CategoryMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMetadataRepository extends JpaRepository<CategoryMetadata,Integer> {

    CategoryMetadata findByCategoryName(String category);
}
