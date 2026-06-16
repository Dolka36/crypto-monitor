package com.cryptomonitor.model;

public class CryptoCoin extends Asset{
    private double priceChange24h;

    public CryptoCoin(String name, String ticker, double price, double priceChange24h) {
        super(name, ticker, price);
        this.priceChange24h = priceChange24h;
    }

    public double getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(double priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    @Override
    public Asset withPrice(double newPrice) {
        return new CryptoCoin(getName(), getTicker(), newPrice, getPriceChange24h());
    }

    @Override
    public String toString() {
        return String.format("Название: %s, Тикер: %s, Цена: %.2f, Изменение за 24ч: %.2f"
                , getName(), getTicker(), getPrice(), getPriceChange24h());
    }
}
