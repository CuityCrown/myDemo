package com.ryml;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.ReadConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:zookeeper测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
public class MongoDBTest {

    @Test
    public void Test(){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        //创建collection
        // mongoDatabase.createCollection("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        //插入数据
        Document document = new Document();
        document.append("id","2");
        document.append("name","yyx");
        document.append("age","25");
        document.append("sex","女");
        student.insertOne(document);

    }

    @Test
    public void test1(){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        //获取collection
        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        //collection数据查询
        FindIterable<Document> documents = student.find();

        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

}

