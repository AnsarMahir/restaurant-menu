package com.toticone.restuarant_menu.repository;

import com.toticone.restuarant_menu.entity.Extra;
import com.toticone.restuarant_menu.enums.ExtraTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExtraRepository extends JpaRepository<Extra,Integer> {
    List<Extra> findByType(ExtraTypes type);
    List<Extra> findByTypeAndNameContaining(ExtraTypes type, String name);

    @Query("SELECT e FROM Extra e WHERE e.name LIKE %:name%")
    List<Extra> findByNameContaining(@Param("name") String name);

}
