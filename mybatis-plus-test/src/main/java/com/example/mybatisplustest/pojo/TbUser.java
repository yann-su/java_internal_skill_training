package com.example.mybatisplustest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_user")
public class TbUser {
    @TableId( type = IdType.AUTO)
    long id;
    @TableField("user_name")
    String UserName;
    String password;
    String name;
    int age;
    String email;
}

