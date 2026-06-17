package com.cryptomonitor.service;

import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Asset;
import org.junit.jupiter.api.Test;
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
}