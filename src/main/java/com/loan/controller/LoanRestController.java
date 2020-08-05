package com.loan.controller;

import com.loan.dao.LoanRepository;
import com.loan.kafka.producer.UpdatePriceProducer;
import com.loan.model.Loan;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "loan")
@RestController
public class LoanRestController {

    @Autowired
    private LoanRepository repository;
    @Autowired
    ConfigurableApplicationContext context;

    @ApiOperation(value = "Get Loan Details By ID", nickname = "getLoan")

    @RequestMapping(method = RequestMethod.GET, path = "/api/getByID", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "Loan Id",
            required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Loan.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Loan getLoan(@RequestParam(value = "id", defaultValue = "1") String id) {
        return repository.findById(id);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/api/getAll", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam()
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Loan.class,responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
        public ResponseEntity<Iterable<?>> getAll() {
        try {
            return new ResponseEntity<Iterable<?>>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<?>>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/createLoan")
    public ResponseEntity<?> addLoan(@RequestBody Loan loan) {
        repository.save(loan);
        return Util.createResponseEntity("Successful creation of a resource", HttpStatus.OK);
    }


    @RequestMapping(value = "/updateLoan", method = RequestMethod.POST)
    public ResponseEntity<String> updateLoan(@RequestBody Loan loan) {

        UpdatePriceProducer producer = context.getBean("kafkaUpdatePriceProducer", UpdatePriceProducer.class);
        String id= loan.getId();
        double newinterestPercentage = loan.getInterestPercentage();
        producer.updateDataPriceAndPublishToKafka(id, newinterestPercentage);

        return Util.createResponseEntity("Successful updation of a resource", HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        repository.delete(id);

        return Util.createResponseEntity("Successful deletion of a resource", HttpStatus.OK);
    }


}

 class Util {

     public static ResponseEntity<String> createResponseEntity(String message, HttpStatus statusCode) {
         return new ResponseEntity<>(message, statusCode);
     }


 }