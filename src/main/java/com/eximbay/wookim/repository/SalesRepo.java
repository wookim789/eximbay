package com.eximbay.wookim.repository;

import com.eximbay.wookim.document.SalesDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepo extends MongoRepository<SalesDoc, String> {
    List<SalesDoc> findByTitle(String title);
}
