package com.cjm.ms;

import java.util.Objects;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantity;
    private int reserve;

    @Override
    public boolean equals(Object obj) {
        boolean output;
        if (this == obj) {
            output = true;
        }
        /* Can either be getClass() != obj.getClass(), or
           !(obj instanceof StockItem).
           The first method means that only instances of the same classes can be equal,
           while the second method allows instances of subclasses to be equal.
         */
        else if (obj == null || getClass() != obj.getClass()) {
            output = false;
        } else {
            StockItem oSI = (StockItem) obj;
            output = name.equals(oSI.name);
        }
        return output;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name + " price: " + String.format("%.2f", price);
    }

    @Override
    public int compareTo(StockItem o) {
        int output;
        if (this == o) {
            output = 0;
        } else if(o != null) {
            output = name.compareTo(o.name);
        } else {
            throw new NullPointerException();
        }
        return output;
    }

    public StockItem(String name, double price) {
        this(name, price, 0);
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantity = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailableQuantity() {
        return quantity - reserve;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void adjustStock(int addStock) {
        int newQuantity = quantity + addStock;
        if (newQuantity >= 0) {
            quantity = newQuantity;
            if (quantity < reserve) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int adjustReserve(int addReserve) {
        int reserved;
        int newReserve = reserve + addReserve;
        if (newReserve < 0) {
            throw new IllegalArgumentException();
        } else if (newReserve <= quantity) {
            reserve = newReserve;
            reserved = addReserve;
        } else { // newReserve > quantity
            reserved = quantity - reserve;
            reserve = quantity;
        }
        return reserved;
    }
}
