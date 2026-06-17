package com.cryptomonitor.service;

import com.cryptomonitor.exception.AssetNotFoundException;
import com.cryptomonitor.model.Asset;
import com.cryptomonitor.model.CryptoCoin;
import com.cryptomonitor.model.Stablecoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PortfolioService<T extends Asset> {
    private final CopyOnWriteArrayList<T> assets = new CopyOnWriteArrayList<>();

    public void addAsset(T asset){
        assets.add(asset);
    }

    public void displayPortfolio(){
        for(T asset : assets){
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

    public T findAssetByTicker(String ticker){
        return assets.stream()
                .filter(asset -> asset.getTicker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElseThrow(() -> new AssetNotFoundException("Ассет с тикером " + ticker + " не найден"));
    }

    public void displayTopExpensiveAssets(){
        assets.stream()
                .sorted(Comparator.comparingDouble(Asset::getPrice).reversed())
                .limit(3)
                .forEach(asset -> System.out.println(asset));
    }

    public CopyOnWriteArrayList<T> getAssets() {
        return assets;
    }
}
