package com.aws.docdb.docdb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepo extends JpaRepository<Users, String> {

    @Query("{name : {$regex : ?0}}")
    Page<Users> findByNickNameLike(String nickName, PageRequest pageable);
}
