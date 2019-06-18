package com.ryml.service;

import com.ryml.entity.Student;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/18
 */
public interface StudentService {

    /**
     * 获取学生列表
     * @return
     */
    public List<Student> getAll();

}
