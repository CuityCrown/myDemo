package com.ryml;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

/**
 * description:MongoDB测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/30
 */
public class MongoDBTest {

    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

    @Test
    public void createCollection(){
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        //创建collection
        mongoDatabase.createCollection("student");

    }

    @Test
    public void insert(){
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

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
    public void delete(){
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        student.deleteOne(Filters.eq("name","yyx"));
    }

    @Test
    public void update(){
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        student.updateOne(Filters.eq("name", "刘一博"), new Document("$set",new Document("name","GuityCrown")));
    }

    @Test
    public void select(){
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

    @Test
    public void test1(){
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        //获取collection
        MongoCollection<Document> student = mongoDatabase.getCollection("student");

    }

}

