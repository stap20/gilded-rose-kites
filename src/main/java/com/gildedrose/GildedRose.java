package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {

    Item[] items;
    Map<String, IItemUpdater> updaters;

    public GildedRose(Item[] items) {
        this.items = items;
        initializeUpdaters();
    }

    private void initializeUpdaters() {
        updaters = new HashMap<>();
        updaters.put("Aged Brie", new AgedBrieUpdater());
        updaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        updaters.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            IItemUpdater updater = updaters.getOrDefault(item.name, new NormalItemUpdater());
            updater.update(item);
        }
    }

}
