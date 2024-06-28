package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItemLosesOneQualityAfterOneDay() {
        Item[] items = new Item[] { new Item("dummy", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void normalItemLosesTwoQualityAfterSellInDate() {
        Item[] items = new Item[] { new Item("dummy", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void itemQualityIsNotNegative() {
        Item[] items = new Item[] { new Item("dummy", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void itemQualityIsNeverAbove50() {
        Item[] items = new Item[] { new Item("dummy", 5, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieIncreasesOneQualityAfterOneDay() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void itemQualityDoesNotIncreaseAbove50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfurasDoesNotDecreaseSellInDate() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void sulfurasDoesNotDecreaseQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstagePassesIncreaseQualityByTwoWhenThereAre10DaysOrLessLeft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void backstagePassesIncreaseQualityByThreeWhenThereAre5DaysOrLessLeft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(26, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityDropsTo0AfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }
}
