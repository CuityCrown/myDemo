package com.ryml.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2020/3/2 17:09
 */
public class HttpClientTest {

    @Test
    public void testPost() {

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 创建Post请求
            HttpPost httpPost = new HttpPost("http://scidev.lenovo.com/sciv4/ControlTower/api/controlTower/controlTowerFilterConf/refreshFilterCache");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Cookie", "GA1.2.2031096836.1577443167; experimentation_subject_id=ImI2MjY2ZTgyLWU2MTYtNGEwZS05N2M2LWRkY2RkMzlhYTYwZCI%3D--c159ad03e6bb87ebb34c0838e4b57a2b96073bc1; _gid=GA1.2.1328674299.1583116253; shiro.session.id=8df03fbe0c7a4478b655a1419b06e3a7; EVENT_MGMT_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOiJ0ZXN0IiwiZ3JwIjoiMTA0IiwidW5tIjoidGVzdCIsImVtbCI6InRlc3RAbGVub3ZvLmNvbSIsInByaSI6IiIsIm1ibCI6IjE4OTAwMDAwMDAwIiwiaXNzIjoiU0NJIEF1dGhlbnRpY2F0aW9uIENlbnRlciIsImlhdCI6MTU4MzE0MTE4MCwiZXhwIjoxNTgzMTg0MzgwfQ.NLbQO1SDY5Q5xid52axwEnONzBMQJmMwhuXWMy3OhJKlyMVS_8cpL3rVQPqf-gsZvXuuYe5xOhhwQn6e_0r9KQ; JSESSIONID=C9F617C51806E9C136BCDE16BEC7C931");
            StringEntity httpEntity = new StringEntity("0");
            httpPost.setEntity(httpEntity);
            // 由客户端执行(发送)Post请求
            httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
