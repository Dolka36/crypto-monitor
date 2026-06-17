package com.cryptomonitor.service;

import com.cryptomonitor.model.Asset;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class PriceVolatilityService implements Runnable {

    private static final Logger logger = Logger.getLogger(PriceVolatilityService.class.getName());
    private final PortfolioService<? extends Asset> portfolioService;

    public PriceVolatilityService(PortfolioService<? extends Asset> portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.severe("Поток обновления цен был прерван: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }

            CopyOnWriteArrayList<? extends Asset> assetsList = portfolioService.getAssets();

            for (int i = 0; i < assetsList.size(); i++) {
                Asset currentAsset = assetsList.get(i);

                double changePercent = ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
                double newPrice = currentAsset.getPrice() * (1 + changePercent / 100);
                newPrice = Math.round(newPrice * 100.0) / 100.0;

                Asset updatedAsset = currentAsset.withPrice(newPrice);

                ((CopyOnWriteArrayList<Asset>) assetsList).set(i, updatedAsset);
            }
            logger.info("Фоновое обновление цен завершено.");
        }
    }
}