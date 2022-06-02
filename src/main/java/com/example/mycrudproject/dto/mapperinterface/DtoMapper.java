package com.example.mycrudproject.dto.mapperinterface;

public interface DtoMapper<U, T> {
    T convertToModel(U dto);
    U convertToDTO(T user);
}
