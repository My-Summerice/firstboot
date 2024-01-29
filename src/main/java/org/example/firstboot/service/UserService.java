package org.example.firstboot.service;

import org.example.firstboot.dto.user.UserDTO;
import org.example.firstboot.entity.User;

import java.util.List;

public interface UserService {
    void register(String name, String password);

    User login(String name, String password);

    List<User> getUserList(int page, int size);

    void addUser(UserDTO userDTO);

    User findUserById(long id);

    User getUserByName(String name);

    void updateUser(UserDTO userDTO);

    void delUser(long id);

    void delUserForever(long id);

    String getUserRole(long id);
}
