package com.ryml.entity;

import lombok.Data;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/20 11:21
 */
@Data
public class Transaction {

    private Trader trader;

    private Integer year;

    private Integer amount;

    public Transaction(Trader trader, Integer year, Integer amount) {
        this.trader = trader;
        this.year = year;
        this.amount = amount;
    }
}
