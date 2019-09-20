package com.ryml.service.impl;

import com.ryml.entity.Menu;
import com.ryml.entity.Student;
import com.ryml.mapper.StudentMapper;
import com.ryml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/18
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = StudentService.class)
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {
        return new ArrayList<>();
        //return studentMapper.getAll();
    }

    @Override
    public List<Menu> getMenu() {
        return studentMapper.getMenu();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    public void insert(Student student) {
        studentMapper.insert(student);
        System.out.println(123);
    }
}
