package com.ryml.controller;

import com.ryml.entity.Menu;
import com.ryml.entity.Student;
import com.ryml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    private StudentService studentService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        return studentService.getAll();
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public List<Menu> getMenu(){
        return studentService.getMenu();
    }

    @RequestMapping("/testDateFormat")
    @ResponseBody
    public Date test1(Date date){
        System.out.println(date);
        return date;
    }

}
