package com.priceactiontrader.priceaction.service;

import com.priceactiontrader.priceaction.model.PriceAction;
import java.util.List;

public interface PriceActionService {
    public PriceAction savePriceAction(PriceAction priceAction);
    public List<PriceAction> getAllPriceAction();
    public void deletePriceAction();
    public void deleteSpecificPriceAction(int id);
    public void saveOrUpdate(PriceAction priceaction, int id);
}
