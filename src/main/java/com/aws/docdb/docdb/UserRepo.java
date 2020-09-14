package com.aws.docdb.docdb;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<Users, String> {

    @Query("{name : {$regex : ?0}}")
    List<Users> findByNickNameLike(String nickName);
}
