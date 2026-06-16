package com.cryptomonitor.model;

public abstract class Asset {
    private final String name;
    private final String ticker;
    private final double price;

    public Asset(String name, String ticker, double price) {
        this.name = name;
        this.ticker = ticker;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public abstract Asset withPrice(double newPrice);


    @Override
    public String toString() {
        return String.format("Название: %s, Тикер: %s, Цена: %.2f", getName(), getTicker(), getPrice());
    }
}
