package com.aws.docdb.docdb;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<Users, Integer> {

    List<Users> findByNickNameLike(String nickName);
    List<Users> findByNicknameLike(String nickName);
}
