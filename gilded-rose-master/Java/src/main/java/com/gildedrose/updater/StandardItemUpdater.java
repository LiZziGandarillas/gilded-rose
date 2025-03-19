package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class StandardItemUpdater extends AbstractItemUpdater {

    public StandardItemUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        decreaseQuality();
    }

    @Override
    protected void updateExpired() {
        if (isExpired()) {
            decreaseQuality();
        }
    }
}
