package com.cryptomonitor.model;

public class Stablecoin extends Asset{
    private String fiatBinding;


    public Stablecoin(String name, String ticker, double price, String fiatBinding) {
        super(name, ticker, price);
        this.fiatBinding = fiatBinding;
    }

    public String getFiatBinding() {
        return fiatBinding;
    }

    public void setFiatBinding(String fiatBinding) {
        this.fiatBinding = fiatBinding;
    }

    @Override
    public String toString() {
        return String.format("Название: %s, Тикер: %s, Цена: %.2f, Тип валюты: %s"
                , getName(), getTicker(), getPrice(), getFiatBinding());
    }
}
