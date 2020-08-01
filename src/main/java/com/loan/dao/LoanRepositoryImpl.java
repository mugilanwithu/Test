package com.loan.dao;

import com.loan.model.Loan;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public class LoanRepositoryImpl implements LoanRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int updateStockProduct(String id, int stock) {

        Query query = new Query();
                query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        update.set("stock", stock);

        WriteResult result = mongoTemplate.updateFirst(query, update, Loan.class, Loan.COLLECTION_NAME);

        return result.getN();
    }

    @Override
    public int updatePriceProduct(String id, double interestPercentage) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        update.set("interestPercentage", interestPercentage);

        WriteResult result = mongoTemplate.updateFirst(query, update, Loan.class, Loan.COLLECTION_NAME);

        return result.getN();
    }
}
