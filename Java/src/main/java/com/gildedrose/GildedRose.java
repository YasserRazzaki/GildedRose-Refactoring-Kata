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

            if (isSulfuras(item)) {
                continue;
            }

            if (isAgedBrie(item)) {
                updateAgedBrie(item);
                continue;
            }

            if (isBackstage(item)) {
                updateBackstage(item);
                continue;
            }
            updateNormalItem(item);

        }
    }

    private void updateNormalItem(Item item) {
        item.sellIn--;
        decreaseQuality(item);
        if(isExpired(item)) {
            decreaseQuality(item);
        }
    }

    private void updateBackstage(Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) {
            increaseQuality(item);
        }

        if (item.sellIn < 6) {
            increaseQuality(item);
        }

        item.sellIn--;

        if (isExpired(item)) {
            item.quality = 0;
        }
    }

    private void updateAgedBrie(Item item) {
        item.sellIn--;
        increaseQuality(item);
        if(isExpired(item)) {
            increaseQuality(item);
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
        item.quality--; }
    }

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals(Sulfuras);
    }

    private static boolean isBackstage(Item item) {
        return item.name.equals(Backstage);
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

}}
