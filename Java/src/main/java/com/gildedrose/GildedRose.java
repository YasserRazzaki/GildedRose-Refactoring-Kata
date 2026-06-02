package com.gildedrose;

class GildedRose {
    public static final String Backstage = "Backstage passes to a TAFKAL80ETC concert";
    public static final String Sulfuras = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(Backstage)) {
                if (item.quality > 0) {
                    if (!item.name.equals(Sulfuras)) {
                        item.quality--;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality++;

                    if (item.name.equals(Backstage)) {
                        if (item.sellIn < 11) increaseQuality(item);

                        if (item.sellIn < 6) increaseQuality(item);
                    }
                }
            }

            if (!item.name.equals(Sulfuras)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(Backstage)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(Sulfuras)) {
                                item.quality--;
                            }
                        }
                    } else {
                        item.quality = 0;
                        // item.quality = item.quality - item.quality;
                    }
                } else increaseQuality(item);
            }
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
