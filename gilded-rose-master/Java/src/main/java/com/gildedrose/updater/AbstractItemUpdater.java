package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public abstract class AbstractItemUpdater implements ItemUpdater {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    @Override
    public void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        updateExpired(item);
    }

    protected abstract void updateQuality(Item item);

    protected void updateSellIn(Item item) {
        item.sellIn--;
    }

    protected abstract void updateExpired(Item item);

    protected void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    protected void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    protected boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}
