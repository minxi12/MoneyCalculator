package Controller;

import Module.*;

import java.io.IOException;

public class Control {

    private Currency from;
    private Currency to;
    private double cantidad;
    private double resRate;

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getResRate() {
        return resRate;
    }

    public Control(Currency base, Currency dest, double cantidad) throws IOException {
        this.from = base;
        this.to = dest;
        this.cantidad = cantidad;
        this.resRate = getRes();
    }

    private Double getRes() throws IOException {
        ExchangeRate rateObj = new ExchangeRate(from, to);
        double rate = rateObj.getRate();
        double res = this.cantidad * rate;
        return res;
    }
}
