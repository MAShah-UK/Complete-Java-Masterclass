package com.cjm.ms;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    // Puts keys in StockItem.compareTo() order.
    private final Map<StockItem, Integer> list = new TreeMap<>();

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        String itemPlural = list.size() == 1 ? " item" : " items";
        output.append("Shopping basket, ").append(name).append(", contains ");
        output.append(list.size()).append(itemPlural).append(":\n");
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> pair: list.entrySet()) {
            output.append('\t').append(pair.getValue()).append(" x ").append(pair.getKey()).append('\n');
            totalCost += pair.getKey().getPrice() * pair.getValue();
        }
        output.append("Total basket cost: ").append(totalCost).append('\n');
        return output.toString();
    }

    public Basket(String name) {
        this.name = name;
    }

    public Map<StockItem, Integer> getItems() {
        return Collections.unmodifiableMap(list);
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }
}
