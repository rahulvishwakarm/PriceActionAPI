package com.priceactiontrader.priceaction.repository;

import com.priceactiontrader.priceaction.model.PriceAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceActionRepository extends JpaRepository<PriceAction,Integer> {

}
