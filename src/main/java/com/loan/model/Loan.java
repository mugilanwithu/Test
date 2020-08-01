package com.loan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;


public class Loan {

    public static final String COLLECTION_NAME = "loan";

    @Id
    private String id;
    private String customerName;
    private double loanAmount;
    private double interestPercentage;
    private String mortageType;

    @Autowired
    public Loan() {
    }
    @Autowired
    public Loan(String id, double interestPercentage) {
        this.id = id;
        this.interestPercentage = interestPercentage;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestPercentage() {
        return interestPercentage;
    }

    public String getMortageType() {
        return mortageType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setInterestPercentage(double interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public void setMortageType(String mortageType) {
        this.mortageType = mortageType;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", loanAmount=" + loanAmount +
                ", interestPercentage=" + interestPercentage +
                ", mortageType='" + mortageType + '\'' +
                '}';
    }
}
