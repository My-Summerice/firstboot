package org.example.firstboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.firstboot.entity.User;

import java.util.List;

public interface UserMapper {
    void register(@Param("name") String name, @Param("password") String password);

    User login(@Param("name") String name, @Param("password") String password);

    List<User> getUserList(@Param("offset") int offset, @Param("size") int size);

    void addUser(@Param("user") User user);

    User findUserByName(@Param("name") String name);

    User findUserById(@Param("id") long id);

    void updateUser(@Param("user") User user);

    void delUser(@Param("id") long id);

    void delUserForever(@Param("id") long id);

    String getUserRole(@Param("id") long id);
}
