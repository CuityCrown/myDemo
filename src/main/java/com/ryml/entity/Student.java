package com.ryml.entity;

import com.ryml.annotation.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
@Data
public class Student extends MyStudent implements Serializable{

    @NotNull(message = "id不能为空")
    private Integer id;

    private String name;

    private Integer age;

    private Date date;

}
