package com.eximbay.wookim.repository;

import com.eximbay.wookim.document.SalesDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesRepo extends MongoRepository<SalesDoc, String> {
    List<SalesDoc> findByTitle(String title);
}
