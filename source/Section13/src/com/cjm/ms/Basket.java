/*
Modify the program so that adding items to the shopping basket doesn't
actually reduce the stock count but, instead, reserves the requested
number of items.

You will need to add a "reserved" field to the StockItem class to store the
number of items reserved.

Items can continue to be added to the basket, but it should not be possible to
reserve more than the available stock of any item. An item's available stock
is the stock count less the reserved amount.

The stock count for each item is reduced when a basket is checked out, at which
point all reserved items in the basket have their actual stock count reduced.

Once checkout is complete, the contents of the basket are cleared.

It should also be possible to "unreserve" items that were added to the basket
by mistake.

The program should prevent any attempt to unreserve more items than were
reserved for a basket.

Add code to Main that will unreserve items after they have been added to the basket,
as well as unreserving items that have not been added to make sure that the code
can cope with invalid requests like that.

After checking out the baskets, display the full stock list and the contents of each
basket that you created.

 */

package com.cjm.ms;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    // Puts keys in StockItem.compareTo() order.
    private final Map<StockItem, Integer> list = new TreeMap<>();
    private final StockList stockList;

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
        output.append("Total basket cost: ").append(String.format("%.2f", totalCost));
        return output.toString();
    }

    public Basket(String name, StockList stockList) {
        this.name = name;
        this.stockList = stockList;
    }

    public Map<StockItem, Integer> getItems() {
        return Collections.unmodifiableMap(list);
    }

    private boolean validRequest(String item, StockItem stockItem, int quantity) {
        boolean isValid = true;
        if (stockItem == null) {
            System.out.println(item + ": not sold.");
            isValid = false;
        } else if (quantity < 1) {
            System.out.println("Invalid quantity requested (<1).");
            isValid = false;
        }
        return isValid;
    }

    public int addToBasket(String item, int quantity) {
        int added = 0;
        StockItem stockItem = stockList.get(item);
        if (!validRequest(item, stockItem, quantity)) {
            added = 0;
        } else if (stockItem.getAvailableQuantity() - quantity < 0) {
            System.out.println(item + ": need more stock.");
        } else {
            int inBasket = list.getOrDefault(stockItem, 0);
            int newInBasket = inBasket + stockItem.adjustReserve(quantity);
            list.put(stockItem, newInBasket);
            added = newInBasket;
        }
        return added;
    }

    public int removeFromBasket(String item, int quantity) {
        int rem = 0; // Removed.
        StockItem stockItem = stockList.get(item);
        if (!validRequest(item, stockItem, quantity)) {
            rem = 0;
        } else if (quantity > list.get(stockItem)) {
            System.out.println("You are trying to remove more " + item + "s than you actually have.");
        } else {
            int inBasket = list.get(stockItem);
            int newInBasket = inBasket - quantity;
            stockItem.adjustReserve(-newInBasket);
            list.put(stockItem, newInBasket);
        }
        return rem;
    }

    public void checkOut() {
        for (Map.Entry<StockItem, Integer> pair: list.entrySet()) {
            StockItem item = pair.getKey();
            int quantitySold = pair.getValue();
            item.adjustReserve(-quantitySold);
            item.adjustStock(-quantitySold);
        }
        list.clear();
    }
}
