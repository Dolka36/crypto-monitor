package com.cryptomonitor.service;

import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Asset;
import com.cryptomonitor.model.Stablecoin;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioServiceTest {

    @Test
    void shouldFindAssetByTicker() {
        PortfolioService<Asset> portfolioService = new PortfolioService<>();
        CryptoCoin btc = new CryptoCoin("Bitcoin", "BTC", 65000, 2.5);
        portfolioService.addAsset(btc);

        Asset found = portfolioService.findAssetByTicker("BTC");

        assertNotNull(found);
        assertEquals("Bitcoin", found.getName());
        assertEquals(65000, found.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenAssetNotFound() {
        PortfolioService<Asset> portfolioService = new PortfolioService<>(); // пустой сервис

        assertThrows(com.cryptomonitor.exception.AssetNotFoundException.class, () -> {
            portfolioService.findAssetByTicker("NON_EXISTENT");
        });
    }

    @Test
    void shouldReturnTopExpensiveAssetsInCorrectOrder() {
        PortfolioService<Asset> portfolioService = new PortfolioService<>();
        CryptoCoin btc = new CryptoCoin("Bitcoin", "BTC", 65000, 2.5);
        CryptoCoin eth = new CryptoCoin("Ethereum", "ETH", 1700, 2.9);
        Stablecoin usdt = new Stablecoin("Tether", "USDT", 1.0, "USD");

        portfolioService.addAsset(eth);
        portfolioService.addAsset(usdt);
        portfolioService.addAsset(btc);

        List<Asset> topAssets = portfolioService.getTopExpensiveAssets(2);

        assertEquals(2, topAssets.size());

        assertEquals("BTC", topAssets.get(0).getTicker());
        assertEquals("ETH", topAssets.get(1).getTicker());
    }
}