package com.vladimir.relexApp.services;

import com.vladimir.relexApp.Mappers.ProductMapper;
import com.vladimir.relexApp.dtos.ChangeListResponseDto;
import com.vladimir.relexApp.dtos.ProducedRequestDto;
import com.vladimir.relexApp.dtos.ProductRequestDto;
import com.vladimir.relexApp.dtos.ProductResponseDto;
import com.vladimir.relexApp.entity.ChangeList;
import com.vladimir.relexApp.entity.Product;
import com.vladimir.relexApp.repositories.ChangeListRepository;
import com.vladimir.relexApp.repositories.ProductRepository;
import com.vladimir.relexApp.utils.JwtTokenUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final JwtTokenUtils jwtTokenUtils;

    private final UserService userService;

    private final ChangeListRepository changeListRepository;

    public ProductService(ProductRepository productRepository, JwtTokenUtils jwtTokenUtils, UserService userService, ChangeListRepository changeListRepository) {
        this.productRepository = productRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userService = userService;
        this.changeListRepository = changeListRepository;
    }

    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsByProductName(productRequestDto.getProductName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Товар с таким именем уже существует");
        }
        Product product = ProductMapper.INSTANCE.productRequestDtoToProduct(productRequestDto);
        productRepository.save(product);
        return ProductMapper.INSTANCE.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll(Sort.by("productName"));
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductResponseDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public ChangeListResponseDto producedStaff(ProducedRequestDto producedRequestDto, String header) {

        Product product = getProducedProduct(producedRequestDto);

        ChangeList changeList = new ChangeList();

        String token = header.substring(7);

        changeList.setUser(userService.findByUserEmail(jwtTokenUtils.getEmail(token)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден")));
        changeList.setProduct(product);
        changeList.setCreated_at(LocalDate.now());
        changeList.setQuantity(producedRequestDto.getQuantity());

        changeListRepository.save(changeList);
        return ChangeListResponseDto.builder()
                .email(jwtTokenUtils.getEmail(token))
                .productName(product.getProductName())
                .producedQuantity(changeList.getQuantity())
                .lastChangeDate(changeList.getCreated_at())
                .build();
    }

    private Product getProducedProduct(ProducedRequestDto producedRequestDto) {
        Product product = productRepository.findByProductName(producedRequestDto.getProductName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Товар с таким именем не найден"));

        product.setProducedQuantity(producedRequestDto.getQuantity());
        product.setLastChangeDate(LocalDate.now());
        productRepository.save(product);
        return product;
    }

    @Transactional
    public void removeProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Товар не найден");
        }
        changeListRepository.deleteByProductId(id);
        productRepository.deleteById(id);

    }

}
