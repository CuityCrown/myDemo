package com.ryml.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ryml.entity.Menu;
import com.ryml.entity.Student;
import com.ryml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Reference
    private StudentService studentService1;
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

    @RequestMapping("/myTest")
    @ResponseBody
    public List<Student> myTest(){
        return studentService1.getAll();
    }

    @RequestMapping("/test")
    public void test() throws ParseException {
        try {
            Student student = new Student();
            student.setId(1);
            student.setName("测试");
            student.setAge(123);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        student.setMyTime(simpleDateFormat.parse("2019-07-03"));
            student.setMyTest("2019-07-01");
            studentService.insert(student);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2017-02-16");
        Date parse1 = simpleDateFormat.parse("2017-02-15");
        boolean before = parse.after(parse1);
        System.out.println(before);
    }

}
