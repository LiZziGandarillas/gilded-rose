package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.updater.*;

/**
 * @author Lizeth Gandarillas
 */
public class ItemUpdaterFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    public ItemUpdater createUpdater(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrieUpdater(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            return new BackstagePassUpdater(item);
        } else if (item.name.equals(SULFURAS)) {
            return new SulfurasUpdater(item);
        } else if (item.name.contains(CONJURED)) {
            return new ConjuredItemUpdater(item);
        } else {
            return new StandardItemUpdater(item);
        }
    }
}
