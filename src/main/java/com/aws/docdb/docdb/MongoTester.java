package com.aws.docdb.docdb;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Pattern;


@RestController
public class MongoTester {

    @Autowired
    MongoTemplate mongoTemplate;


    @GetMapping("/native")
    public String nat(){

        ServerAddress address = new ServerAddress("junjie-doc-db.cluster-cb22o7m9zgip.ap-southeast-1.docdb.amazonaws.com", 27017);
        MongoCredential credential = MongoCredential.createCredential("junjie",
                "junjie", "Kadan1016".toCharArray());

        MongoClient client = new MongoClient(address, Arrays.asList(credential));

        System.out.println("now ....");
        long start = System.currentTimeMillis();


        DB mongoDb = client.getDB("junjie");
        DBCollection doc = mongoDb.getCollection("users");
        BasicDBObject cond = new BasicDBObject("$regex", "^1000*");
        DBCursor cursor = doc.find(cond);
        cursor.hint("nickname_index");
        Iterator it = cursor.iterator();
        while(it.hasNext()){
            Document doc1 = (Document) it.next();
            System.out.println(doc1.get("nickName"));
        }
        long end = System.currentTimeMillis();
        System.out.println("time cost for the query :::: " + (end - start));
        return "hello";
    }


    @GetMapping("/my")
    public String saythis(){



//        Criteria criteria = new Criteria();
//        criteria.where("1=1");
//        criteria.and("nickName").regex("^" + "CCTV*");
//        Query query = null;
//        query = Query.query(criteria).withHint("{nickName:1}").limit(10);

        String tableName = "users";
       // String sql = "[{$match:{nickName:'^CCTV*'}},{'$hint':{nickname_index:1}},{'$limit':10}]";
//        String sql = "[{$match:{nickName:'^CCTV*'}},{'$limit':10}]";
//        List<BasicDBObject> basicDBObjectList = JSONArray.parseArray(sql, BasicDBObject.class);
//        FindIterable<Document> mycoll = mongoTemplate.getDb().getCollection(tableName).find(Filters.regex("nickName","^1000*")).hintString("nickname_index");
//        Iterator it = mycoll.iterator();
//        while(it.hasNext()){
//            Document doc1 = (Document) it.next();
//            System.out.println(doc1.get("nickName"));
//        }


        MongoClient mongoClient =
                new MongoClient(
                        new MongoClientURI("mongodb://junjie:Kadan1016@junjie-doc-db.cluster-cb22o7m9zgip.ap-southeast-1.docdb.amazonaws.com:27017/?authSource=admin&ssl=false"));

        System.out.println("now ....");
        long start = System.currentTimeMillis();

        MongoDatabase db = mongoClient.getDatabase("junjie");
        FindIterable<Document> mycoll = db.getCollection("users").find().hintString("nickname_index:1");
        Iterator it = mycoll.iterator();
        while(it.hasNext()){
            Document doc1 = (Document) it.next();
            System.out.println(doc1.get("nickName"));
        }

        long end = System.currentTimeMillis();

        System.out.println("time cost for the query :::: " + (end - start));
//        List<Users> users =  mongoTemplate.find(query, Users.class);
//
//       if(users!=null)
//           users.parallelStream().forEach(u->System.out.println(u.getNickName()));


        return "done";
    }


    @GetMapping("/hello")
    public String sayHello(){

        String template = "mongodb://%s:%s@%s/junjiee?replicaSet=rs0&readpreference=%s";
        String username = "junjie";
        String password = "Kadan1016";
        String clusterEndpoint = "junjie-doc-db.cluster-cb22o7m9zgip.ap-southeast-1.docdb.amazonaws.com:27017";
        String readPreference = "secondaryPreferred";
        String connectionString = String.format(template, username, password, clusterEndpoint, readPreference);

        MongoClientURI clientURI = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase testDB = mongoClient.getDatabase("junjie");

        Document doc = new Document();
        doc.append("nickName","^CCTV*");

        FindIterable<Document> mycoll = testDB.getCollection("users").find(Filters.regex("nickName","^CCTV*")).hintString("nickname_1").limit(10);

        Iterator it = mycoll.iterator();
        while(it.hasNext()){
            Document doc1 = (Document) it.next();
            System.out.println(doc1.get("nickName"));
        }

//        Criteria criteria = new Criteria();
//        criteria.where("1=1");
//        criteria.and("nickName").regex("^" + "CCTV*");
//        Query query = null;
//        query = Query.query(criteria).withHint("nickname_1").limit(10);
//        MongoTemplate.
//        List<~> result = mongoTemplate.find(query, clz, collection);

        return "helloworld";
    }
}
