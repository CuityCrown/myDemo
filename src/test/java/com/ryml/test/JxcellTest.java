/*
package com.ryml.test;

import com.jxcell.CellException;
import com.jxcell.View;
import org.junit.Test;

import java.io.IOException;

*/
/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/24 22:20
 *//*

public class JxcellTest {

    */
/**
     * jxcell 加密 只适用于二进制的excel(.xls结尾) xml版本(.xlsx)会报错
     * @throws IOException
     * @throws CellException
     *//*

    @Test
    public void encrypt() {
        View view = new View();
        try {
            view.read("D:/test.xls");
            view.write("D:/test.xls","123456");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CellException e) {
            e.printStackTrace();
        }
    }

}
*/
