package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class ConjuredItemUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        decreaseQuality(item);
        decreaseQuality(item);
    }

    @Override
    protected void updateExpired(Item item) {
        if (isExpired(item)) {
            decreaseQuality(item);
            decreaseQuality(item);
        }
    }
}
