package com.loan.controller;

import com.loan.dao.LoanRepository;
import com.loan.kafka.producer.UpdatePriceProducer;
import com.loan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanRepository repository;

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public ModelAndView updateStockInventory(@ModelAttribute("id") String id, @ModelAttribute("stock") int stock){
        int retval = repository.updateStockProduct(id, stock);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "editStock/{id}", method = RequestMethod.GET)
    public String updateStockInventory(Model model, @PathVariable String id){
        Loan loan = repository.findById(id);

        model.addAttribute(loan);
        return "updateStock";
    }

    @RequestMapping(value = "editPrice/{id}", method = RequestMethod.GET)
    public String updatePriceInventory(Model model, @PathVariable String id){
        Loan loan = repository.findById(id);

        model.addAttribute(loan);

        return "updatePrice";
    }

    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    public ModelAndView updatePriceInventory(@ModelAttribute("id") String id, @ModelAttribute("interestPercentage") double newinterestPercentage){
        UpdatePriceProducer producer = context.getBean("kafkaUpdatePriceProducer",UpdatePriceProducer.class);

        producer.updateDataPriceAndPublishToKafka(id, newinterestPercentage);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addInventory(@ModelAttribute("loan") Loan loan, Model model){
        repository.save(loan);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView addInventory(HttpSession httpSession){
        return new ModelAndView("create");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable String id){
        repository.delete(id);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/getAll")
    public ModelAndView getAll(HttpSession httpSession){
        List<Loan> result = repository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("loans", result);

        return modelAndView;
    }

    @RequestMapping(value = "/getByID", method = RequestMethod.GET)
    public Loan getInventory(@RequestParam(value = "id", defaultValue = "1") String id) {
        return repository.findById(id);
    }
}
