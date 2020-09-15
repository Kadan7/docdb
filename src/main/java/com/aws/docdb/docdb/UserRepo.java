package com.aws.docdb.docdb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepo extends MongoRepository<Users, String> {

    @Query("{nickName : {$regex : ?0}}")
    List<Users> findTop10CustomByRegExNickName(String nickName);
}
