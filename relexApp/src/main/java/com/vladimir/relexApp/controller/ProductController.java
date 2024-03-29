package com.vladimir.relexApp.controller;

import com.vladimir.relexApp.dto.ProducedRequestDto;
import com.vladimir.relexApp.dto.ProductRequestDto;
import com.vladimir.relexApp.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для взаимодействия с товарами")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Добавляет новый товар",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.addProduct(productRequestDto));
    }

    @Operation(
            summary = "Выводит список всех товаров",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            summary = "Метод для сотрудников, позволяющий вносить количество произведенного товара",
            description = "Уровень доступа USER"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody ProducedRequestDto producedRequestDto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.producedStaff(producedRequestDto, token));
    }

    @Operation(
            summary = "Удаление продукта по id",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.ok().build();
    }
}
