package com.loan.controller;

import com.loan.dao.LoanRepository;
import com.loan.model.Loan;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;


@RequestMapping(value = "loan")
@RestController
public class LoanRestController {

    @Autowired
    private LoanRepository repository;

    @ApiOperation(value = "GetLoanData", nickname = "getLoan")

    @RequestMapping(method = RequestMethod.GET, path = "/api/getByID", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "Loan Id",
            required = false, dataType = "string", paramType = "query", defaultValue = "1")
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
    public List getAll(@RequestParam() String id) {
        return (List) repository.findAll();
    }
}
