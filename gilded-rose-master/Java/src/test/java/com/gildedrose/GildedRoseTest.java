package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void standardItemDecreasesQualityAndSellIn() {
        Item[] items = new Item[]{new Item("Standard Item", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    void qualityDegradesTwiceAsFastAfterExpiration() {
        Item[] items = new Item[]{new Item("Standard Item", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(18, items[0].quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item[] items = new Item[]{new Item("Standard Item", 10, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void agedBrieIncreasesInQualityAsItGetsOlder() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    void agedBrieQualityIncreasesDoubleAfterExpiration() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    void qualityNeverIncreasesPastFifty() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void sulfurasNeverChanges() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void backstagePassesIncreaseInQuality() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    void backstagePassesIncreaseBy2WhenTenDaysOrLess() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    void backstagePassesIncreaseBy3WhenFiveDaysOrLess() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    void backstagePassesQualityDropsToZeroAfterConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFast() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(18, items[0].quality);
    }

    @Test
    void conjuredItemsDegradeFourTimesAsFastAfterExpiration() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(16, items[0].quality);
    }
}
