package com.cryptomonitor;


import com.cryptomonitor.exception.AssetNotFoundException;
import com.cryptomonitor.model.Asset;
import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Stablecoin;
import com.cryptomonitor.service.PortfolioService;
import com.cryptomonitor.service.PriceVolatilityService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PortfolioService<Asset> portfolioService = new PortfolioService<>();
        portfolioService.addAsset(new CryptoCoin("Bitcoin", "BTC", 65000, +2.5));
        portfolioService.addAsset(new CryptoCoin("Ethereum", "ETH", 1700, +2.9));
        portfolioService.addAsset(new Stablecoin("Tether", "USDT", 1.0, "USD"));
        portfolioService.addAsset(new Stablecoin("USD Coin", "USDC", 1.0, "USD"));

        PriceVolatilityService volatilityService = new PriceVolatilityService(portfolioService);


        Thread exchangeThread = new Thread(volatilityService);
        exchangeThread.setDaemon(true);
        exchangeThread.start();

        portfolioService.displayPortfolio();

        System.out.println("--------------------------");

        try {
            Asset fake = portfolioService.findAssetByTicker("Tether");
            System.out.println("Найдено: " + fake.getName());
        } catch (AssetNotFoundException e) {
            System.out.println("[Ошибка поиска]: " + e.getMessage());
        }

        System.out.println("--------------------------");

        portfolioService.displayTopExpensiveAssets();

        System.out.println("--------------------------");

        Thread.sleep(5000);
        portfolioService.displayPortfolio();
    }
}