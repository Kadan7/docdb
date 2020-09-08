package com.aws.docdb.docdb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;


@RestController
public class MongoTester {
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

        FindIterable<Document> mycoll = testDB.getCollection("users").find(doc).hintString("nickname_1").limit(10);

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
