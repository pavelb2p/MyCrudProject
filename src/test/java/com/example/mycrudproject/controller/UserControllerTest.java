package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private static final User user = new User(11L, "Elza", 36);
    private static final User newUser = new User(11L, "Elza", 36);
    private static final String userJSON = "{\"id\":11,\"name\":\"Elza\",\"age\":36}";
    private static final List<User> usersList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void shouldSaveUserWhenPostMethodTest() throws Exception {
        when(userService.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/users/save")
                        .content(userJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().json(userJSON)
                );
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void shouldGetListUsersWhenGetMethodTest() throws Exception {
        usersList.add(user);
        when(userService.getUsers()).thenReturn(Optional.of(usersList));
        mockMvc.perform(get("/users/list")
                        .content(userJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()
                );
        verify(userService, times(1)).getUsers();
    }

    @Test
    void shouldUpdateUserWhenPutMethodTest() throws Exception {
        when(userService.updateUser(newUser, newUser.getId())).thenReturn(newUser);
        mockMvc.perform(put("/users/{userId}/update", 11)
                        .content(userJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk());
        verify(userService, times(1)).updateUser(newUser, newUser.getId());
    }

    @Test
    void shouldDeleteUserWhenDeleteMethod() throws Exception {
        mockMvc.perform(delete("/users/{userId}/delete", 11)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}