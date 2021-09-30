package com.example.mybatisplustest;

import com.example.mybatisplustest.mapper.TbUserMapper;
import com.example.mybatisplustest.pojo.TbUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusTestApplicationTests {

    @Resource
    TbUserMapper tbUserMapper;


    @Test
    //单纯插入
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

    @Test
        //单纯插入并且获取id
    void insertAndGetId() {
        TbUser tbUser = new TbUser();
        tbUser.setUserName("lis");
        tbUser.setAge(13);
        tbUser.setName("3131");
        tbUser.setEmail("313131@");
        tbUser.setPassword("31231");
        int insert = tbUserMapper.insert(tbUser);
        long id = tbUser.getId();
        System.out.println(id);
    }



    @Test
    //更新操作
    void updateUser() {
        TbUser tbUser = new TbUser();
        tbUser.setUserName("修改数据");
        tbUser.setAge(13);
        tbUser.setName("3131");
        tbUser.setEmail("eewqeq@");
        tbUser.setPassword("31231");
        tbUser.setId(2);
        //根据id更新
        int i = tbUserMapper.updateById(tbUser);
        long id = tbUser.getId();
        System.out.println(tbUser);
    }


    //控制更新的字段
    void testUpdateUser2(){
        TbUser tbUser = new TbUser();
        //只更新非null的字段
        tbUser.setUserName("修改数据");
        //判断字段是否要修改到set语句是根据属性值是否为null
        //UPDATE tb_user SET user_name=?, password=?, name=?, age=?, email=? WHERE id=?
        tbUser.setAge(23);
        tbUser.setId(2);
        tbUserMapper.updateById(tbUser);

    }


    //主键删除
    @Test
    void testDeleteById(){
        TbUser tbUser = new TbUser();
        tbUser.setId(1);
        tbUserMapper.deleteById(tbUser.getId());

    }


    //按照条件删除
    @Test
    void testDeleteById1(){
        Map<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("user_name","修改数据");
        tbUserMapper.deleteByMap(stringStringHashMap);

    }

    //按照条件删除
    @Test
    void testDeleteById2(){
        Map<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("user_name","修改数据");
        tbUserMapper.deleteByMap(stringStringHashMap);

    }


    //按照条件删除
    @Test
    void testDeleteById3(){
        Collection<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        List<Integer> collect = Stream.of(1, 2, 4).collect(Collectors.toList());
        tbUserMapper.deleteBatchIds(collect);

    }



    //查询操作比较多
    //按照条件删除
    @Test
    void testSelectById3(){
        TbUser tbUser = tbUserMapper.selectById(4);
        System.out.println(tbUser);

    }





}
