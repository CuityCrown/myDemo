package com.ryml.entity;

import com.ryml.annotation.NotNull;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
@Data
public class MyStudent {

    @NotNull(message = "sex不能为空")
    private Integer sex;

}
