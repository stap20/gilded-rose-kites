package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    private GildedRose setupAndUpdate(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

    @Test
    void shouldCreateCorrectItemObject() {
        Item[] items = new Item[]{new Item("item", 5, 10)};

        GildedRose app = new GildedRose(items);

        assertEquals("item", app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void shouldDegradeTwice() {
        Item[] items = new Item[]{
            new Item("item1", 0, 5),
            new Item("item2", -1, 5)
        };

        GildedRose app = setupAndUpdate(items);

        assertEquals(3, app.items[0].quality);
        assertEquals(3, app.items[1].quality);
    }

    @Test
    void shouldNotHaveNegativeQuality() {
        Item[] items = new Item[]{
            new Item("item1", 0, 0),
            new Item("item1", -1, 0),};

        GildedRose app = setupAndUpdate(items);

        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    void shouldNotExceedMaximumQuality() {
        Item[] items = new Item[]{
            new Item("Aged Brie", 1, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50)
        };

        GildedRose app = setupAndUpdate(items);

        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    void shouldDecreaseSellInDays() {
        Item[] items = new Item[]{
            new Item("Aged Brie", 1, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 1),
            new Item("test item", 1, 1),
            new Item("test item2", 0, 1)
        };

        GildedRose app = setupAndUpdate(items);

        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[1].sellIn);
        assertEquals(0, app.items[2].sellIn);
        assertEquals(-1, app.items[3].sellIn);
    }

    @Test
    void shouldNotChangeSulfurasSellInOrQuality() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 50)};
        GildedRose app = setupAndUpdate(items);

        assertEquals(10, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void shouldIncreaseAgedBrieQuality() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 0),new Item("Aged Brie",0, 0)};
        GildedRose app = setupAndUpdate(items);

        assertEquals(1, app.items[0].quality);
        assertEquals(2, app.items[1].quality);
    }

    @Test
    void shouldHandleBackstagePassesQuality() {
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)
        };

        int[] expectedQuality = {11, 12, 12, 13, 13, 0, 0};

        GildedRose app = setupAndUpdate(items);

        for (int i = 0; i < items.length; i++) {
            assertEquals(expectedQuality[i], app.items[i].quality);
        }
    }

    @Test
    void shouldHandleVariousItems() {
        Item[] items = new Item[]{
            new Item("item1", 5, 10),
            new Item("Aged Brie", 2, 0),
            new Item("Sulfuras, Hand of Ragnaros", 5, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)
        };

        int[] expectedQuality = {9, 1, 80, 21, 23};
        GildedRose app = setupAndUpdate(items);

        for (int i = 0; i < items.length; i++) {
            assertEquals(expectedQuality[i], app.items[i].quality);
        }
    }
}
