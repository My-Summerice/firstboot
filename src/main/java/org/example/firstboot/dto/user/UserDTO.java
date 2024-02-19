package org.example.firstboot.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UserDTO {
    @NotNull(message = "缺少用户id", groups = {UpdateValidation.class, FindUserByIdValidation.class, DelValidation.class})   // 指定需要校验的分组，方便分接口做参数校验
    @Min(value = 1, message = "id异常，超出范围!", groups = {UpdateValidation.class, FindUserByIdValidation.class, DelValidation.class})
    private long id;

    @NotEmpty(message = "缺少用户名称", groups = {RegisterValidation.class, AddValidation.class, UpdateValidation.class})
    @Length(min = 2, max = 10, message = "用户名长度不合法", groups = {RegisterValidation.class, AddValidation.class, UpdateValidation.class})
    private String name;

    @NotEmpty(message = "缺少用户密码", groups = {RegisterValidation.class})
    @Length(min = 6, max = 20, message = "密码长度不合法", groups = RegisterValidation.class)
    private String password;

    @NotNull(message = "缺少用户年龄", groups = {AddValidation.class, UpdateValidation.class})
    @PositiveOrZero(message = "用户年龄超出范围!", groups = {AddValidation.class, UpdateValidation.class})     // 非负数
    private int age;

    @Range(min = 1, max = 3, message = "性别选项异常，超出可选范围!", groups = {AddValidation.class, UpdateValidation.class})
    private int gender = 3; // 默认性别未知，若请求带有该字段将会覆盖默认值
}
