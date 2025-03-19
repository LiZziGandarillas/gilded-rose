package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class SulfurasUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        // No change in quality
    }

    @Override
    protected void updateSellIn(Item item) {
        // No change in sellIn
    }

    @Override
    protected void updateExpired(Item item) {
        // Legendary items never expire
    }
}
