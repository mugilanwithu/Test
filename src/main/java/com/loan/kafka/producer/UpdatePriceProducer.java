package com.loan.kafka.producer;


public interface UpdatePriceProducer {

    void updateDataPriceAndPublishToKafka(String id, double interestPercentage);
}
