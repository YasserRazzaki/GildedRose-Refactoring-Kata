package com.gildedrose;

class GildedRose {
    // 1. Constantes & Attributs
    public static final String Backstage = "Backstage passes to a TAFKAL80ETC concert";
    public static final String Sulfuras = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String Conjured = "Conjured Mana Cake";

    Item[] items;

    // 2. Constructeur
    public GildedRose(Item[] items) {
        this.items = items;
    }

    // 3. Point d'entrée principal (Le Routeur)
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

            if (isConjured(item)) {
                updateConjuredItem(item);
                continue;
            }

            updateNormalItem(item);
        }
    }

    // 4. Logiques Métiers Spécifiques (Niveau d'abstraction Moyen)
    private void updateAgedBrie(Item item) {
        item.sellIn--;
        increaseQuality(item);
        if (isExpired(item)) {
            increaseQuality(item);
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

    private void updateConjuredItem(Item item) {
        item.sellIn--;
        decreaseQuality(item);
        decreaseQuality(item);

        if (isExpired(item)) {
            decreaseQuality(item);
            decreaseQuality(item);
        }
    }

    private void updateNormalItem(Item item) {
        item.sellIn--;
        decreaseQuality(item);
        if (isExpired(item)) {
            decreaseQuality(item);
        }
    }

    // 5. Prédicats d'Identification (Basses couches)
    private static boolean isSulfuras(Item item) {
        return item.name.equals(Sulfuras);
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private static boolean isBackstage(Item item) {
        return item.name.equals(Backstage);
    }

    private static boolean isConjured(Item item) {
        return item.name.equals(Conjured);
    }

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    // 6. Mutateurs de Qualité (Basses couches)
    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
