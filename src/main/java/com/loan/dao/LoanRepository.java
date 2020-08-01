package com.loan.dao;

import com.loan.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LoanRepository extends MongoRepository<Loan, String>, LoanRepositoryCustom {

    public List<Loan> findAll();

    public Loan findById(String id);
}
