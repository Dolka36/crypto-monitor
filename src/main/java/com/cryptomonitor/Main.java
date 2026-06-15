package com.cryptomonitor;


import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Stablecoin;
import com.cryptomonitor.service.PortfolioService;

public class Main {
    public static void main(String[] args) {
        PortfolioService portfolioService = new PortfolioService();
        portfolioService.addAsset(new CryptoCoin("Bitcoin", "BTC", 65000, +2.5));
        portfolioService.addAsset(new Stablecoin("Tether", "USDT", 1.0, "USD"));

        portfolioService.displayPortfolio();
    }
}