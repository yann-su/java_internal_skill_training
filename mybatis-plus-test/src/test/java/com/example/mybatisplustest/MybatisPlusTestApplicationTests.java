package com.example.mybatisplustest;

import com.example.mybatisplustest.mapper.TbUserMapper;
import com.example.mybatisplustest.pojo.TbUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusTestApplicationTests {

    @Resource
    TbUserMapper tbUserMapper;


    @Test
    void insertMybatisPlus() {
        TbUser tbUser = new TbUser();

        tbUser.setUserName("zhangsan");
        tbUser.setAge(10);
        tbUser.setName("sss");
        tbUser.setEmail("23131@");
        tbUser.setPassword("s2e21124");
        int insert = tbUserMapper.insert(tbUser);
        System.out.println(insert);
    }




}
