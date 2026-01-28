package com.rushi.demo.controller;

import com.rushi.demo.dto.ApiResponse;
import com.rushi.demo.entity.User;
import com.rushi.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<Page<User>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name
    ) {

        Page<User> users = userService.getUsers(page, size, sortBy, direction, name);

        return new ApiResponse<>(
                true,
                "Users fetched successfully",
                users
        );
    }
}
