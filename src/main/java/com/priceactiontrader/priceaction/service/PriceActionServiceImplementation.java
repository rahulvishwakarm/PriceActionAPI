package com.priceactiontrader.priceaction.service;

import com.priceactiontrader.priceaction.model.PriceAction;
import com.priceactiontrader.priceaction.repository.PriceActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceActionServiceImplementation implements PriceActionService {
    @Autowired
    private PriceActionRepository priceActionRepository;

    @Override
    public PriceAction savePriceAction(PriceAction priceAction) {
        return priceActionRepository.save(priceAction);
    }

    @Override
    public List<PriceAction> getAllPriceAction() {
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
    public void saveOrUpdate(PriceAction priceaction, int id) {
        PriceAction getPriceAction = priceActionRepository.getReferenceById(id);
        getPriceAction.setDescription(priceaction.getDescription());
        getPriceAction.setPublishedAt(priceaction.getPublishedAt());
        getPriceAction.setTitle(priceaction.getTitle());
        getPriceAction.setUrl(priceaction.getUrl());
        priceActionRepository.save(getPriceAction);
    }
}
