package com.inventory.dao;

import com.inventory.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by eko.j.manurung on 6/2/2016.
 */
public interface LoanRepository extends MongoRepository<Loan, String>, LoanRepositoryCustom {

    public List<Loan> findAll();

    public Loan findById(String id);
}
