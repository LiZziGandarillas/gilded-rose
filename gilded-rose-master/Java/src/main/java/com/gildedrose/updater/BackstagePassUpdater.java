package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public class BackstagePassUpdater extends AbstractItemUpdater {

    public BackstagePassUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();

        if (item.sellIn < 11) {
            increaseQuality();
        }

        if (item.sellIn < 6) {
            increaseQuality();
        }
    }

    @Override
    protected void updateExpired() {
        if (isExpired()) {
            item.quality = 0;
        }
    }
}
