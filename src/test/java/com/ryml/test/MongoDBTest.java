package com.ryml;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:MongoDB测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/30
 */
public class MongoDBTest {

    MongoClient mongoClient = new MongoClient("10.120.112.164", 27017);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void createCollection() {
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        //创建collection
        mongoDatabase.createCollection("student");

    }

    @Test
    public void insert() {
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");
        //插入数据
        Document document = new Document();
        document.append("id", "2");
        document.append("name", "yyx");
        document.append("age", "25");
        document.append("sex", "女");
        student.insertOne(document);
    }

    @Test
    public void delete() {
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        student.deleteOne(Filters.eq("name", "yyx"));
    }

    @Test
    public void update() {
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        MongoCollection<Document> student = mongoDatabase.getCollection("student");

        student.updateOne(Filters.eq("name", "刘一博"), new Document("$set", new Document("name", "GuityCrown")));
    }

    @Test
    public void select() {
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
    public void test1() {
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

        //获取collection
        MongoCollection<Document> student = mongoDatabase.getCollection("student");


    }

    @Test
    public void test() {
        MongoDatabase database = mongoClient.getDatabase("digital_sc_p");

        //获取collection
        MongoCollection<Document> student = database.getCollection("Material_Price_Tracking_RawData");

        System.out.println(student);
    }


    public void testMongoGroup(Integer column) {
        Map<String, Object> map = new HashMap<>(2);
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.project("item", "date", "cashOffer", "previousClose"),
                Aggregation.match(Criteria.where("item").is("item")),
                Aggregation.group("date").max("date").as("date").max("item").as("item").sum("cashOffer").as("cashOffer").sum("previousClose").as("previousClose"),
                Aggregation.sort(Sort.Direction.DESC, "date"),
                Aggregation.limit(90),
                Aggregation.project("item", "date", "cashOffer", "previousClose")
        );
        AggregationResults<JSONObject> data = mongoTemplate.aggregate(agg, "Material_Price_Tracking_RawData", JSONObject.class);
        List<JSONObject> mappedResults = data.getMappedResults();
        Double value = null;
        Double regression = null;
        if (CollectionUtils.isNotEmpty(mappedResults)) {
            if (column == 1) {
                value = mappedResults.get(0).getDoubleValue("");
                regression = BigDecimal.valueOf(value).subtract(BigDecimal.valueOf(mappedResults.get(mappedResults.size() - 1).getDoubleValue(""))).divide(BigDecimal.valueOf(value), 2, RoundingMode.UP).doubleValue();
            } else if (column == 2) {
                value = mappedResults.get(0).getDoubleValue("");
                regression = BigDecimal.valueOf(value).subtract(BigDecimal.valueOf(mappedResults.get(mappedResults.size() - 1).getDoubleValue(""))).divide(BigDecimal.valueOf(value), 2, RoundingMode.UP).doubleValue();
            }
        }
        map.put("value", value);
        map.put("regression", regression);
    }
}

