package com.javatech.spring.cloud.sql.api.repo;

import com.javatech.spring.cloud.sql.api.repo.models.Author;
import com.javatech.spring.cloud.sql.api.repo.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends CrudRepository<Author,Long> {
    List<Author> findAll();
}
