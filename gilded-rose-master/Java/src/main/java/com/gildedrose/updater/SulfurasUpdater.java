package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class SulfurasUpdater extends AbstractItemUpdater {

    public SulfurasUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        // No change in quality
    }

    @Override
    protected void updateSellIn() {
        // No change in sellIn
    }

    @Override
    protected void updateExpired() {
        // Legendary items never expire
    }
}
