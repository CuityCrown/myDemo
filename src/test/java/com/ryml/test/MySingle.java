package com.ryml.test;

import com.ryml.common.BizException;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2020/2/26 22:45
 */
public class MySingle {
    public MySingle(){
        throw new BizException("");
    }

    public static MySingle2 getMySingle(){
        return MySingle2.mySingle2;
    }
    static class MySingle2{
        final static MySingle2 mySingle2 = new MySingle2();
    }

}
