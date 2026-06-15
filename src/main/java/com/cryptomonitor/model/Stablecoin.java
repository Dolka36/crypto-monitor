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
}
