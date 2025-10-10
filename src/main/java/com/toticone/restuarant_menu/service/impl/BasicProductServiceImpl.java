package com.toticone.restuarant_menu.service.impl;

import com.toticone.restuarant_menu.controller.WebSocketController;
import com.toticone.restuarant_menu.dto.BasicProductDTO;
import com.toticone.restuarant_menu.dto.ExtraDTO;
import com.toticone.restuarant_menu.entity.BasicProduct;
import com.toticone.restuarant_menu.entity.Extra;
import com.toticone.restuarant_menu.repository.BasicProductRepository;
import com.toticone.restuarant_menu.repository.ExtraRepository;
import com.toticone.restuarant_menu.service.BasicProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BasicProductServiceImpl implements BasicProductService {

    @Autowired
    private BasicProductRepository productRepository;

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private ExtraRepository extraRepository;

    @Override
    public List<BasicProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BasicProductDTO getProductById(int id) {
        BasicProduct product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return convertToDTO(product);
    }

    @Override
    public BasicProductDTO createProduct(BasicProductDTO productDTO) {
        BasicProduct product = convertToEntity(productDTO);
        for (ExtraDTO extraDTO : productDTO.getExtras()) {
            Extra extra = convertExtraDTOToExtra(extraDTO);
            Extra savedextra = extraRepository.save(extra);
            product.addExtra(savedextra);
        }
        BasicProduct savedProduct = productRepository.save(product);

        BasicProductDTO result = convertToDTO(savedProduct);
        webSocketController.sendProductUpdate(result);
        return result;
    }

    @Override
    public BasicProductDTO updateProduct(int id, BasicProductDTO productDTO) {
        BasicProduct existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setType(productDTO.getType());

        BasicProduct updatedProduct = productRepository.save(existingProduct);
        BasicProductDTO result = convertToDTO(updatedProduct);
        webSocketController.sendProductUpdate(result);
        return result;
    }

    @Override
    public void deleteProduct(int id) {
        BasicProduct product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
        webSocketController.sendProductDelete(id);
    }

    @Override
    public List<BasicProductDTO> getProductsByType(int type) {
        return productRepository.findByType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BasicProductDTO> getProductsByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BasicProductDTO addExtraToProduct(int productId, int extraId) {
        BasicProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra not found"));

        product.getExtras().add(extra);
        BasicProduct updatedProduct = productRepository.save(product);
        BasicProductDTO result = convertToDTO(updatedProduct);
        webSocketController.sendProductUpdate(result);
        return result;
    }

    @Override
    public BasicProductDTO removeExtraFromProduct(int productId, int extraId) {
        BasicProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra not found"));

        product.getExtras().remove(extra);
        BasicProduct updatedProduct = productRepository.save(product);
        BasicProductDTO result = convertToDTO(updatedProduct);
        webSocketController.sendProductUpdate(result);
        return result;
    }

    // Helper methods for conversion
    private BasicProductDTO convertToDTO(BasicProduct product) {
        BasicProductDTO dto = new BasicProductDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setType(product.getType());

        // Convert extras to DTOs
        if (product.getExtras() != null) {
            List<ExtraDTO> extraDTOs = product.getExtras().stream()
                    .map(this::convertExtraToDTO)
                    .collect(Collectors.toList());
            dto.setExtras(extraDTOs);
        }

        return dto;
    }

    private BasicProduct convertToEntity(BasicProductDTO dto) {
        BasicProduct product = new BasicProduct();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setType(dto.getType());
        List<Extra> extra = new ArrayList<>();
        for (ExtraDTO extraDTO : dto.getExtras()) {
            Extra result = new Extra();
            result.setName(extraDTO.getName());
            result.setType(extraDTO.getType());
        }
        product.setExtras(extra);
        return product;
    }

    private ExtraDTO convertExtraToDTO(Extra extra) {
        ExtraDTO dto = new ExtraDTO();
        dto.setId(extra.getId());
        dto.setName(extra.getName());
        dto.setType(extra.getType());
        return dto;
    }

    private Extra convertExtraDTOToExtra(ExtraDTO extraDTO) {
        Extra extra = new Extra();
        extra.setType(extraDTO.getType());
        extra.setName(extraDTO.getName());
        return extra;
    }

}
