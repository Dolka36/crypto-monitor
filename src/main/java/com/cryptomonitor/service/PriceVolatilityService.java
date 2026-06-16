package com.cryptomonitor.service;

import com.cryptomonitor.model.Asset;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PriceVolatilityService implements Runnable{
    private final PortfolioService portfolioService;

    public PriceVolatilityService(PortfolioService portfolioService1) {
        this.portfolioService = portfolioService1;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            CopyOnWriteArrayList<Asset> assetsList = portfolioService.getAssets();

            for (int i = 0; i < assetsList.size(); i++) {
                Asset currentAsset = assetsList.get(i);

                double changePercent = ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
                double newPrice = currentAsset.getPrice() * (1 + changePercent / 100);
                newPrice = Math.round(newPrice * 100.0) / 100.0;

                Asset updatedAsset = currentAsset.withPrice(newPrice);

                assetsList.set(i, updatedAsset);
            }
            System.out.println("[Биржа]: Фоновое обновление цен завершено.");
        }
    }
}
