package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void degragadesTwice(){
        Item [] items = new Item[]{new Item("name",0,5)};

        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        System.out.println("Run Degragades test");

        assertEquals(3,gildedRose.items[0].quality);
    }

}
