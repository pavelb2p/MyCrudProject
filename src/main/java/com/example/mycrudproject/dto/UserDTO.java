package com.example.mycrudproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name shouldn't be empty")
    @Size(min = 2,max = 15, message = "Name should be between 2 and 15 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;
}