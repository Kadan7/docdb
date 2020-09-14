package com.aws.docdb.docdb;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<Users, String> {

    List<Users> findByNickNameLike(String nickName);
}
