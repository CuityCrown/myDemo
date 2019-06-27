package com.ryml.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
@Data
public class ValidateResult {

    private boolean result;

    private List<String> messages = new ArrayList<>();

    public void addMessage(String message){
        this.messages.add(message);
    }
}
