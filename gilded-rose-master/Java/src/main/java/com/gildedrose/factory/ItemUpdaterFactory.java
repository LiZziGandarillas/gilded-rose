package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.updater.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Lizeth Gandarillas
 */
public class ItemUpdaterFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    private final Map<Predicate<Item>, Function<Item, ItemUpdater>> strategies = new LinkedHashMap<>();

    public ItemUpdaterFactory() {
        strategies.put(item -> item.name.equals(AGED_BRIE), item -> new AgedBrieUpdater());
        strategies.put(item -> item.name.equals(BACKSTAGE_PASSES), item -> new BackstagePassUpdater());
        strategies.put(item -> item.name.equals(SULFURAS), item -> new SulfurasUpdater());
        strategies.put(item -> item.name.contains(CONJURED), item -> new ConjuredItemUpdater());
    }

    public ItemUpdater createUpdater(Item item) {
        for (Map.Entry<Predicate<Item>, Function<Item, ItemUpdater>> entry : strategies.entrySet()) {
            if (entry.getKey().test(item)) {
                return entry.getValue().apply(item);
            }
        }
        return new StandardItemUpdater();
    }
}
