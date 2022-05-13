package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.exception.UserNotFoundException;
import com.example.mycrudproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final User user = new User(11L, "Elza", 36);

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new UserServiceImpl(userRepository);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        when(userRepository.save(any())).thenReturn(new User());
        sut.createUser(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void shouldGetAllUsersSuccessfully() {
        userRepository.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListIfUserIsNotPresent() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThat(userRepository.findAll()).isNotNull();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateIfUserIsFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        sut.updateUser(user, user.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test()
    void shouldUpdateIfUserIsNonFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> sut.updateUser(user, user.getId()))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Can't update! User with".concat(String.valueOf(user.getId())).concat("not found"));
    }

    @Test
    void shouldDeleteIfUserIsFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        sut.deleteUser(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void shouldThrowsExceptionIfUserNotFoundWhenDelete() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> sut.deleteUser(user.getId())).isInstanceOf(UserNotFoundException.class)
                .hasMessage("Can't delete! User with ".concat(String.valueOf(user.getId())).concat("not found"));
    }
}