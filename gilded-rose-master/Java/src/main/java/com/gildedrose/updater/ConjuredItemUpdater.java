package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class ConjuredItemUpdater extends AbstractItemUpdater {

    public ConjuredItemUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        decreaseQuality();
        decreaseQuality();
    }

    @Override
    protected void updateExpired() {
        if (isExpired()) {
            decreaseQuality();
            decreaseQuality();
        }
    }
}
