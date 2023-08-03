package com.example.mycrudproject.dto.mapperdto;

import com.example.mycrudproject.dto.UserDTO;
import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.dto.mapperinterface.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements DtoMapper<UserDTO, User> {
    private final ModelMapper modelMapper = new ModelMapper();

    public User convertToModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
