package com.priceactiontrader.priceaction.service;

import com.priceactiontrader.priceaction.model.PriceAction;
import com.priceactiontrader.priceaction.model.PriceActionM1;
import com.priceactiontrader.priceaction.repository.PriceActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceActionServiceImplementation implements PriceActionService {
    @Autowired
    private PriceActionRepository priceActionRepository;

    @Override
    public PriceActionM1 savePriceAction(PriceActionM1 priceAction) {
        return priceActionRepository.save(priceAction);
    }

    @Override
    public List<PriceActionM1> savePriceActions(List<PriceActionM1> priceActions) {
        return priceActionRepository.saveAll(priceActions);
    }

    @Override
    public List<PriceActionM1> getAllPriceAction() {
        return priceActionRepository.findAll();
    }

    @Override
    public void deletePriceAction() {
        priceActionRepository.deleteAll();
    }

    @Override
    public void deleteSpecificPriceAction(int id) {
        priceActionRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(PriceActionM1 priceaction, int id) {
        PriceActionM1 getPriceAction = priceActionRepository.getReferenceById(id);
        getPriceAction.setDescription(priceaction.getDescription());
        getPriceAction.setPublishedAt(priceaction.getPublishedAt());
        getPriceAction.setTitle(priceaction.getTitle());
        getPriceAction.setUrl(priceaction.getUrl());
        priceActionRepository.save(getPriceAction);
    }
}
