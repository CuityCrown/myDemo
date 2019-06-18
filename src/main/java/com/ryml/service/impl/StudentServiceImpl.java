package com.ryml.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ryml.enums.RedisCommonEnum;
import com.ryml.entity.Student;
import com.ryml.mapper.StudentMapper;
import com.ryml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/18
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(RedisCommonEnum.STUDENT.getValue());
        List<Student> students;
        if (s != null){
            students = JSONObject.parseObject(s, new TypeReference<List<Student>>(){});

        }else{
            students = studentMapper.getAll();
            jedis.set(RedisCommonEnum.STUDENT.getValue(), JSONObject.toJSONString(students));
        }
        return students;
    }
}
