package com.xgen.interview;

import java.util.LinkedHashMap;

import static com.xgen.interview.ShoppingCart.DisplayType.*;


/**
* 1. total line
*    keep adding the price of each item in the for loop
*
* 2. print items in order scanned
*    used linked hash map to keep the key value pairs but in an order.
*    constant time for adding and removing entries
*
* 3. option for price to be displayed first
*    add a way to support this which allows for other formatting options in the future.
*    added enum to list different display formats and the branch can select which one they need
*
* 4. Updated tests to cover all new elements
 *   added new tests to cover new code, current coverage is 100%
 */


public class ShoppingCart implements IShoppingCart {
    private final LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    private final Pricer pricer;

    public enum DisplayType {
        DEFAULT,
        PRICE_FIRST
    }

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (contents.containsKey(itemType))
            number += contents.get(itemType);
        contents.put(itemType, number);
    }

    // the default case that prints in the normal format
    // item - quantity - price
    public void printReceipt() {
        printReceipt(DEFAULT);
    }
    public void printReceipt(DisplayType display) {

        float totalPrice = 0f;
        int totalItems = 0;

        for (String item : contents.keySet()) {
            float priceFloat = ((float) pricer.getPrice(item) * contents.get(item)) / 100;
            totalPrice += priceFloat;
            totalItems += contents.get(item);
            String priceString = String.format("€%.2f", priceFloat);

            switch (display) {
                case DEFAULT:
                    System.out.println(item + " - " + contents.get(item) + " - " + priceString);
                    break;
                case PRICE_FIRST:
                    System.out.println(priceString + " - " + contents.get(item) + " - " + item);
                    break;
            }
        }
        System.out.println("\nTotal Price - " + totalItems + " - " + String.format("€%.2f", totalPrice));

    }

}
