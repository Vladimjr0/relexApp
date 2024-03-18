package com.vladimir.relexApp.mapper;

import com.vladimir.relexApp.dto.ProductRequestDto;
import com.vladimir.relexApp.dto.ProductResponseDto;
import com.vladimir.relexApp.dto.RegistrationUserDto;
import com.vladimir.relexApp.dto.UserResponseDto;
import com.vladimir.relexApp.entity.Product;
import com.vladimir.relexApp.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T10:39:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class ApiMapperImpl implements ApiMapper {

    @Override
    public Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductName( productRequestDto.getProductName() );
        product.setProductQuantity( productRequestDto.getProductQuantity() );
        product.setMeasurementSystem( productRequestDto.getMeasurementSystem() );

        product.setCreatedDate( java.time.LocalDate.now() );
        product.setLastChangeDate( java.time.LocalDate.now() );
        product.setProducedQuantity( 0 );

        return product;
    }

    @Override
    public ProductResponseDto productToProductResponseDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto.ProductResponseDtoBuilder productResponseDto = ProductResponseDto.builder();

        productResponseDto.id( product.getId() );
        productResponseDto.productName( product.getProductName() );
        productResponseDto.productQuantity( product.getProductQuantity() );
        productResponseDto.producedQuantity( product.getProducedQuantity() );
        productResponseDto.measurementSystem( product.getMeasurementSystem() );
        productResponseDto.lastChangeDate( product.getLastChangeDate() );

        return productResponseDto.build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        Integer avrRating = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        avrRating = user.getAvrRating();

        UserResponseDto userResponseDto = new UserResponseDto( id, username, email, avrRating );

        return userResponseDto;
    }

    @Override
    public User registrationUserDtoToUser(RegistrationUserDto registrationUserDto) {
        if ( registrationUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( registrationUserDto.getUsername() );
        user.setEmail( registrationUserDto.getEmail() );
        user.setPassword( registrationUserDto.getPassword() );

        return user;
    }
}
