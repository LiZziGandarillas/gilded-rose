package com.gildedrose;

import com.gildedrose.factory.ItemUpdaterFactory;
import com.gildedrose.updater.ItemUpdater;

class GildedRose {
    private final ItemUpdaterFactory updaterFactory;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        this.updaterFactory = new ItemUpdaterFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = updaterFactory.createUpdater(item);
            updater.updateItem(item);
        }
    }
}
