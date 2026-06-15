package com.cryptomonitor.service;

import com.cryptomonitor.model.Asset;

import java.util.concurrent.ThreadLocalRandom;

public class PriceVolatilityService implements Runnable{
    private final PortfolioService portfolioService;

    public PriceVolatilityService(PortfolioService portfolioService1) {
        this.portfolioService = portfolioService1;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(Asset asset : portfolioService.getAssets()){
                double currentPrice = asset.getPrice();
                double changePercent = ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
                double newPrice = currentPrice * (1 + changePercent / 100);
                newPrice = Math.round(newPrice * 100.0) / 100.0;
                asset.setPrice(newPrice);
            }
            System.out.println("[Биржа]: Фоновое обновление цен завершено.");
        }
    }
}
