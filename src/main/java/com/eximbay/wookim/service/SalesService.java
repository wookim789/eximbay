package com.eximbay.wookim.service;

import com.eximbay.wookim.document.SalesDoc;
import com.eximbay.wookim.repository.SalesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class SalesService {
//    private final SalesRepo salesRepo;
    @Autowired
    private SalesRepo salesRepo;

    public List<SalesDoc> getSales(String title) throws Exception{
        return salesRepo.findByTitle(title);
    }

    public void insertSale(SalesDoc body) throws Exception{
        salesRepo.insert(body);
    }

    public void insertSales(List<SalesDoc> bodyList) throws Exception{
        salesRepo.saveAll(bodyList);
    }
}
