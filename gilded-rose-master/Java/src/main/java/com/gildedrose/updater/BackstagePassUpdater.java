package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class BackstagePassUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) {
            increaseQuality(item);
        }

        if (item.sellIn < 6) {
            increaseQuality(item);
        }
    }

    @Override
    protected void updateExpired(Item item) {
        if (isExpired(item)) {
            item.quality = 0;
        }
    }
}
