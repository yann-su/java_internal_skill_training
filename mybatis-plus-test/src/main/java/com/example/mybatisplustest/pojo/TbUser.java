package com.example.mybatisplustest.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
    Integer age;
    String email;
    @TableLogic
    Integer deleted;

}

