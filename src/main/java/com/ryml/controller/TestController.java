package com.ryml.controller;

import com.alibaba.fastjson.JSONObject;
import com.ryml.entity.Student;
import com.ryml.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
@Controller
@RequestMapping("/student")
public class TestController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        Jedis resource = jedisPool.getResource();
        System.out.println("测试Jedis"+ JSONObject.toJSONString(resource));
        return studentMapper.getAll();
    }

}
