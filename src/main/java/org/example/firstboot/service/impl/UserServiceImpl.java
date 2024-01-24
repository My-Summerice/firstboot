package org.example.firstboot.service.impl;

import org.example.firstboot.core.exception.CustomException;
import org.example.firstboot.dto.user.UserDTO;
import org.example.firstboot.entity.User;
import org.example.firstboot.mapper.UserMapper;
import org.example.firstboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String name, String password) {
        userMapper.register(name, password);
    }

    @Override
    public User login(String name, String password) {
        return userMapper.login(name, password);
    }

    @Override
    public List<User> getUserList(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.getUserList(offset, size);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        if (userMapper.findUserByName(userDTO.getName()) != null) {
            throw new CustomException("名称已存在，请重新输入!");  // 抛出自定义异常
        }
        userMapper.addUser(user);
    }

    @Override
    public User findUserById(long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userMapper.updateUser(convertToEntity(userDTO));
    }

    @Override
    public void delUser(long id) {
        userMapper.delUser(id);
    }

    @Override
    public void delUserForever(long id) {
        userMapper.delUserForever(id);
    }

    /**
     * DTO对象转Entity对象
     */
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());

        return user;
    }
}
