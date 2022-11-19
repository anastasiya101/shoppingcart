package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.xgen.interview.ShoppingCart.DisplayType.*;
import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    // runs with 100% coverage
    @Test
    public void addingNoItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.addItem("apple", 0);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));


        String result = myOut.toString();

        assertEquals("", result);
    }


        @Test
    public void addingAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%n%nTotal Price - 1 - €1.00%n"), myOut.toString());
    }

    @Test
    public void testingNegativeItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.addItem("apple", -2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));


        String result = myOut.toString();

        assertEquals("", result );

    }

    @Test
    public void addingMultipleItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%n%nTotal Price - 2 - €2.00%n"), myOut.toString());
    }

    @Test
    public void addingDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%n%nTotal Price - 3 - €4.00%n"), result);

    }

    @Test
    public void preservesOrder(){

        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        assertEquals(String.format("apple - 4 - €4.00%nbanana - 1 - €2.00%n%nTotal Price - 5 - €6.00%n"), result);


    }
    @Test
    public void testingNonExistentItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%n%nTotal Price - 2 - €0.00%n"), myOut.toString());
    }

    @Test
    public void defaultDisplayTypes(){
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(DEFAULT);

        String result = myOut.toString();

        assertEquals(String.format("apple - 4 - €4.00%nbanana - 1 - €2.00%n%nTotal Price - 5 - €6.00%n"), result);

    }
    @Test
    public void priceFirstDisplayTypes(){
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);
        sc.addItem("crisps", 10);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(PRICE_FIRST);
        String result = myOut.toString();

        assertEquals(String.format("€4.00 - 4 - apple%n€2.00 - 1 - banana%n€0.00 - 10 - crisps%n%nTotal Price - 15 - €6.00%n"), result);

    }

}


