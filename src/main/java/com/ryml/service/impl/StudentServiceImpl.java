package com.ryml.service.impl;

import com.ryml.entity.Student;
import com.ryml.mapper.StudentMapper;
import com.ryml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {
        return studentMapper.getAll();
    }
}
