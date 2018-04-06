package com.cjm.ms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list = new HashMap<>();;

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        double totalValue = 0.0;
        // Entry set returns a key-value pair.
        for (Map.Entry<String, StockItem> pair : list.entrySet()) {
            StockItem item = pair.getValue();
            double value = item.getPrice() * item.getQuantity();
            totalValue += value;

            output.append("Item: ").append(item.getName());
            output.append(" Quantity: ").append(item.getQuantity());
            output.append(" Value: ").append(value);
            output.append('\n');
        }
        output.append("Total stock value: ").append(totalValue).append('\n');
        return output.toString();
    }

    public Map<String, StockItem> getList() {
        // Returns a map that can't be modified. In other words, an immutable map, or read-only map.
        return Collections.unmodifiableMap(list);
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public int addStock(StockItem item) {
        if (item != null) {
            // If the item exists in the map, use that, otherwise use item.
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If the item was already in the map, adjust its quantity.
            if (inStock != item) { // Directly comparing references to see if its the same object.
                item.adjustStock(inStock.getQuantity());
            }
            // Will either put new item or update existing item.
            list.put(item.getName(), item);
            return item.getQuantity();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if (inStock != null && quantity > 0 && inStock.getQuantity() >= quantity) {
            inStock.adjustStock(-quantity);
            return quantity;
        } else {
            return 0;
        }
    }
}
