package com.loan.dao;


public interface LoanRepositoryCustom {

    public int updateStockProduct(String id, int stock);

    public int updatePriceProduct(String id, double interestPercentage);
}
