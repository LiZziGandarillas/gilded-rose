package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class AgedBrieUpdater extends AbstractItemUpdater {

    public AgedBrieUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
    }

    @Override
    protected void updateExpired() {
        if (isExpired()) {
            increaseQuality();
        }
    }
}
