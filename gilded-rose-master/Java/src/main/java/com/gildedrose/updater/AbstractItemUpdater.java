package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * @author Lizeth Gandarillas
 */
public abstract class AbstractItemUpdater implements ItemUpdater {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;
    protected Item item;

    public AbstractItemUpdater(Item item) {
        this.item = item;
    }

    @Override
    public void updateItem() {
        updateQuality();
        updateSellIn();
        updateExpired();
    }

    protected abstract void updateQuality();

    protected void updateSellIn() {
        item.sellIn--;
    }

    protected abstract void updateExpired();

    protected void increaseQuality() {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }
}
