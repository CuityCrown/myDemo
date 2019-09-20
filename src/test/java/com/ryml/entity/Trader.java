package com.ryml.entity;

import lombok.Data;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/20 11:20
 */
@Data
public class Trader {

    private String name;

    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
