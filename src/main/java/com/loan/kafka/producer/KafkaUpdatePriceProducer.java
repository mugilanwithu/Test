package com.loan.kafka.producer;

import com.loan.dao.LoanRepository;
import com.loan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class KafkaUpdatePriceProducer implements UpdatePriceProducer {

    @Autowired
    private LoanRepository repository;

    private MessageChannel toKafka;

    @Autowired
    public KafkaUpdatePriceProducer(@Qualifier("toKafka") MessageChannel toKafka){
        this.toKafka = toKafka;
    }

    @Override
    public void updateDataPriceAndPublishToKafka(String id, double interestPercentage) {

        Loan loan = repository.findById(id);
        //check if new price < old price (diskon)
        //send to kafka
        if(interestPercentage < loan.getInterestPercentage()){
            //system.out.println ("inside loop");
            Message<?> content = new GenericMessage<String>(Double.toString(interestPercentage));
            toKafka.send(content);
        }
        repository.updatePriceProduct(id, interestPercentage);
    }
}
