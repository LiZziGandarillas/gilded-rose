package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.updater.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lizeth Gandarillas
 */
public class ItemUpdaterFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    private static final Map<String, ItemUpdater> strategies = new HashMap<>();

    static {
        strategies.put(AGED_BRIE, new AgedBrieUpdater());
        strategies.put(BACKSTAGE_PASSES, new BackstagePassUpdater());
        strategies.put(SULFURAS, new SulfurasUpdater());

        strategies.put(CONJURED, new ConjuredItemUpdater());
    }

    public static ItemUpdater createUpdater(Item item) {
        return strategies.getOrDefault(item.name, new StandardItemUpdater());
    }
}
