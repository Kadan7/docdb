package com.aws.docdb.docdb;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Pattern;


@RestController
public class MongoTester {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepo userRepo;


    @SuppressWarnings("rawtypes")
    @GetMapping("/my/{nickName}")
    public String saythis(@PathVariable String nickName){





       // String sql = "[{$match:{nickName:'^CCTV*'}},{'$hint':{nickname_index:1}},{'$limit':10}]";
//        String sql = "[{$match:{nickName:'^CCTV*'}},{'$limit':10}]";
//        List<BasicDBObject> basicDBObjectList = JSONArray.parseArray(sql, BasicDBObject.class);



//
//        PageRequest pageRequest = PageRequest.of(1, 10);
//        List<Users> users = userRepo.findByNickName("/^1000/");
//
//        if(users!=null)
//            System.out.println(users.size());
//
//
        long start = System.currentTimeMillis();
//
//        List<Users> users = userRepo.findTop10CustomByRegExNickName("^"+nickName + "*");

        FindIterable<Document> mycoll = mongoTemplate.getDb().getCollection("users").find(Filters.regex("nickName","^1000*")).limit(10);

        try{
        FindIterable<Document> mycoll1 =   mycoll.hintString("{nickName:1}");
            MongoCursor myso = mycoll1.cursor();
            while(myso.hasNext()){
                myso.next();
            }

        }catch(Exception e){
            System.out.println("hint nickName:1 failed");
            try{
                System.out.println("tyring nickName");
                FindIterable<Document> mycoll2 =   mycoll.hintString("nickName");
                MongoCursor myso = mycoll2.cursor();
                while(myso.hasNext()){
                    myso.next();
                }
            }catch(Exception e2){
                System.out.println("hint nickName failed");
                try{
                FindIterable<Document> mycoll3 =   mycoll.hintString("nickName_1");
                    MongoCursor myso = mycoll3.cursor();
                    while(myso.hasNext()){
                        myso.next();
                    }
                }catch(Exception e3){
                    System.out.println("hint nickName_1 failed");
                }
            }
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
