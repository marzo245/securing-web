package com.example.securing_web.repository;

import com.example.securing_web.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends MongoRepository<Model, String> {
  Model findByUsername(String username);
}
