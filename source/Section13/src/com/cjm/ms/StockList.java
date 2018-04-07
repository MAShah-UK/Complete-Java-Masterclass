package com.cjm.ms;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    // Will preserve insertion order.
    private final Map<String, StockItem> items = new LinkedHashMap<>();

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("The following products are in stock:\n");
        double totalValue = 0.0;
        // Entry set returns a key-value pair.
        for (Map.Entry<String, StockItem> pair : items.entrySet()) {
            StockItem item = pair.getValue();
            double value = item.getPrice() * item.getQuantity();
            totalValue += value;

            output.append("\t[Item] ").append(item.getName());
            output.append(" [Quantity] ").append(item.getQuantity());
            output.append(" [Value] ").append(String.format("%.2f", value));
            output.append('\n');
        }
        output.append("Total stock value: ").append(String.format("%.2f", totalValue));
        return output.toString();
    }

    public Map<String, StockItem> getItems() {
        // Returns a map that can't be modified. In other words, an immutable map, or read-only map.
        return Collections.unmodifiableMap(items);
    }

    public StockItem get(String key) {
        return items.get(key);
    }

    public int addStock(StockItem item) {
        if (item != null) {
            // If the item exists in the map, use that, otherwise use item.
            StockItem inStock = items.getOrDefault(item.getName(), item);
            // If the item was already in the map, adjust its quantity.
            if (inStock != item) { // Directly comparing references to see if its the same object.
                item.adjustStock(inStock.getQuantity());
            }
            // Will either put new item or update existing item.
            items.put(item.getName(), item);
            return item.getQuantity();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = items.getOrDefault(item, null);
        if (inStock != null && quantity > 0 && inStock.getQuantity() >= quantity) {
            inStock.adjustStock(-quantity);
            return quantity;
        } else {
            return 0;
        }
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> pair: items.entrySet()) {
            prices.put(pair.getKey(), pair.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }
}
