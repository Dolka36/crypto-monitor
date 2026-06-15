package com.cryptomonitor.service;

import com.cryptomonitor.exception.AssetNotFoundException;
import com.cryptomonitor.model.Asset;
import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Stablecoin;

import java.util.ArrayList;
import java.util.List;

public class PortfolioService {
    private List<Asset> assets = new ArrayList<>();

    public void addAsset(Asset asset){
        assets.add(asset);
    }

    public void displayPortfolio(){
        for(Asset asset : assets){
            System.out.println("Название: " + asset.getName() + " Тикер " + asset.getTicker() + " Цена "
                    + asset.getPrice());
            if(asset instanceof CryptoCoin crypto){
                System.out.println("Изменения за 24ч: " + crypto.getPriceChange24h());
            } else if (asset instanceof Stablecoin stable){
                System.out.println("Тип валюты:  " + stable.getFiatBinding());
            }
            System.out.println("-------------------");
        }
    }

    public Asset findAssetByTicker(String ticker){
        return assets.stream()
                .filter(asset -> asset.getTicker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElseThrow(() -> new AssetNotFoundException("Ассет с тикером " + ticker + " не найден"));
    }
}
