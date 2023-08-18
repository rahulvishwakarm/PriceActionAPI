package com.priceactiontrader.priceaction.controller;

import com.priceactiontrader.priceaction.model.PriceAction;
import com.priceactiontrader.priceaction.service.PriceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/priceaction")
@CrossOrigin
public class PriceActionTraderController {
    @Autowired
    private PriceActionService priceActionService;

    // get all priceaction from end point
    // http://localhost:{{port}}/api/v1/priceaction
    @GetMapping("/getAll")
    public List<PriceAction> getAllNewsArticles() {
        return priceActionService.getAllPriceAction();
    }

    // add new priceaction to get point
    // http://localhost:{{port}}/api/v1/priceaction/add
    @PostMapping("/add")
    public String add(@RequestBody PriceAction priceaction) {
        priceActionService.savePriceAction(priceaction);
        return "PriceAction Added Succesfully";
    }

    // delete all priceaction at end point
    // http://localhost:{{port}}/api/v1/priceaction
    @DeleteMapping("/")
    public String deleteAll() {
        priceActionService.deletePriceAction();
        return "All PriceAction Deleted Succesfully";
    }

    // delete specific priceaction by id
    // http://localhost:{{port}}/api/v1/priceaction/{id}
    @DeleteMapping("/{id}")
    public String deleteSpecific(@PathVariable("id") PriceAction priceaction) {
        priceActionService.deleteSpecificPriceAction(priceaction.getId());
        return "PriceAction "+priceaction.getId()+" is deleted Succesfully!!";
    }

    // http://localhost:{{port}}/api/v1/priceaction/{id}
    @PutMapping("/{id}")
    public String updatePriceAction(@RequestBody PriceAction priceaction, @PathVariable("id") PriceAction priceactions) {
        priceActionService.saveOrUpdate(priceaction,priceactions.getId());
        return "PriceAction "+priceactions.getId()+" is Updated Succesfully!!";
    }

}
