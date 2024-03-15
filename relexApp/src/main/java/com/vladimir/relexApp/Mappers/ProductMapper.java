package com.vladimir.relexApp.Mappers;

import com.vladimir.relexApp.dtos.*;
import com.vladimir.relexApp.entity.Product;
import com.vladimir.relexApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper  {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "lastChangeDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "producedQuantity", expression = "java(0)")
    Product productRequestDtoToProduct(ProductRequestDto productRequestDto);

    ProductResponseDto productToProductResponseDto(Product product);

    UserResponseDto userToUserResponseDto(User user);

    User registrationUserDtoToUser(RegistrationUserDto registrationUserDto);
}
