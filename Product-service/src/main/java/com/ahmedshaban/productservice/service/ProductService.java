package com.ahmedshaban.productservice.service;

import com.ahmedshaban.productservice.dto.ProductRequest;
import com.ahmedshaban.productservice.dto.ProductResponse;
import com.ahmedshaban.productservice.model.Product;
import com.ahmedshaban.productservice.repository.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepo.save(product);
        log.info("product {} saved",product.getId());
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> productList =  productRepo.findAll();
        return productList.stream().map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
