package com.example.portfoliospring1.controller;

import com.example.portfoliospring1.controller.response.BaseResponse;
import com.example.portfoliospring1.domain.dto.UserDto;
import com.example.portfoliospring1.domain.dto.request.AddUserDto;
import com.example.portfoliospring1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-user")
    public BaseResponse<UserDto> getUser(@RequestParam String nickname) {
        return new BaseResponse<>(userService.getUser(nickname));
    }

    @GetMapping("/get-users")
    public BaseResponse<List<UserDto>> getUser() {
        return new BaseResponse<>(userService.getUsers());
    }

    @PostMapping("/add-user")
    public BaseResponse<Long> addUser(@RequestBody AddUserDto addUserDto) {
        return new BaseResponse<>(userService.addUser(addUserDto));
    }
}
