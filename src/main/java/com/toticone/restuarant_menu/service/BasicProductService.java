package com.toticone.restuarant_menu.service;

import com.toticone.restuarant_menu.dto.BasicProductDTO;
import com.toticone.restuarant_menu.entity.BasicProduct;
import java.util.List;

public interface BasicProductService {
    List<BasicProductDTO> getAllProducts();
    BasicProductDTO getProductById(int id);
    BasicProductDTO createProduct(BasicProductDTO productDTO);
    BasicProductDTO updateProduct(int id, BasicProductDTO productDTO);
    void deleteProduct(int id);
    List<BasicProductDTO> getProductsByType(int type);
    BasicProductDTO addExtraToProduct(int productId, int extraId);
    BasicProductDTO removeExtraFromProduct(int productId, int extraId);
    List<BasicProductDTO> getProductsByCategory(String category);
    void changeCategory(String oldCategory, String newCategory);
    String getCategory(int id);
}
