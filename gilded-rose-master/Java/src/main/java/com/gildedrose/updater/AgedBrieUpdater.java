package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class AgedBrieUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        increaseQuality(item);
    }

    @Override
    protected void updateExpired(Item item) {
        if (isExpired(item)) {
            increaseQuality(item);
        }
    }
}
