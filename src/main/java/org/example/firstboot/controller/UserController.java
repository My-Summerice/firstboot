package org.example.firstboot.controller;

import org.apache.ibatis.jdbc.Null;
import org.example.firstboot.core.result.GlobalResult;
import org.example.firstboot.dto.user.*;
import org.example.firstboot.entity.User;
import org.example.firstboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public GlobalResult<Null> register(@Validated(RegisterValidation.class) @RequestBody UserDTO userDTO) {
        userService.register(userDTO.getName(), userDTO.getPassword());
        return GlobalResult.success();
    }

    @PostMapping("/login")
    public GlobalResult<User> login(@Validated(RegisterValidation.class) @RequestBody UserDTO userDTO) {
        return GlobalResult.success(userService.login(userDTO.getName(), userDTO.getPassword()));
    }

    @GetMapping("/list")
    public GlobalResult<List<User>> getUserList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return GlobalResult.success(userService.getUserList(page, size));
    }

    @PostMapping("/add")
    public GlobalResult<String> addUser(@Validated(AddValidation.class) @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return GlobalResult.success();
    }

    @GetMapping("/id")
    public GlobalResult<User> findUserById(@Validated(FindUserByIdValidation.class) @RequestParam() long id) {
        return GlobalResult.success(userService.findUserById(id));
    }

    @PostMapping("/update")
    public GlobalResult<String> updateUser(@Validated(UpdateValidation.class) @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return GlobalResult.success();
    }

    @PostMapping("/del")
    public GlobalResult<String> delUser(@Validated(DelValidation.class) @RequestBody UserDTO userDTO) {
        userService.delUser(userDTO.getId());
        return GlobalResult.success();
    }

    @PostMapping("/del-forever")
    public GlobalResult<String> delUserForever(@Validated(DelValidation.class) @RequestBody UserDTO userDTO) {
        userService.delUserForever(userDTO.getId());
        return GlobalResult.success();
    }
}
