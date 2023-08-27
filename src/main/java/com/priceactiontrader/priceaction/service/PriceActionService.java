package com.priceactiontrader.priceaction.service;

import com.priceactiontrader.priceaction.model.PriceAction;
import com.priceactiontrader.priceaction.model.PriceActionM1;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.awt.print.Pageable;
import java.util.List;

public interface PriceActionService {
    public PriceActionM1 savePriceAction(PriceActionM1 priceAction);
    public List<PriceActionM1> savePriceActions(List<PriceActionM1> priceActions);
    public List<PriceActionM1> getAllPriceAction();
    public void deletePriceAction();
    public void deleteSpecificPriceAction(int id);
    public void saveOrUpdate(PriceActionM1 priceaction, int id);
}
