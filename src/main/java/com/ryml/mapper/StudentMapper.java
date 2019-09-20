package com.ryml.mapper;

import com.ryml.entity.Menu;
import com.ryml.entity.Student;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
public interface StudentMapper {

    List<Student> getAll();

    List<Menu> getMenu();

    void insert(Student student);
}
