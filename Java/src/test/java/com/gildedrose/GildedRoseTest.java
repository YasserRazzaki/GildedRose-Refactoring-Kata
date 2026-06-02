package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("fixme", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    private Item[] createItems(Item... items) {
        return items;
    }

    private void updateQuality(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

@Nested
@DisplayName("Items normaux")
class NormalItems {

    @Test
    @DisplayName("qualité et sellIn diminuent de 1 chaque jour")
    void quality_and_sellIn_decrease_by_1() {
        Item[] items = createItems(new Item("Normal Item", 10, 20));
        updateQuality(items);
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    @DisplayName("qualité diminue 2x plus vite après date d'expiration")
    void quality_decreases_twice_as_fast_after_sell_date() {
        Item[] items = createItems(new Item("Nouveau Item", 0, 0));
        updateQuality(items);
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("qualité n'est jamais négative")
    void quality_never_negative() {
        Item[] items = createItems(new Item("test item", 4, 0));
        updateQuality(items);
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("qualité n'est jamais négative même après expiration")
    void quality_never_negative_after_sell_date() {
        Item[] items = createItems(new Item("test item", 1, 0));
        updateQuality(items);
        assertEquals(0, items[0].quality);
    }
}

    @Nested
    @DisplayName("Aged Brie")
    class AgedBrieTests {

        @Test
        @DisplayName("qualité augmente avec le temps")
        void quality_increases_over_time() {
            Item[] items = createItems(new Item("Aged Brie", 10, 20));
            updateQuality(items);
            assertEquals(21, items[0].quality);
        }

        @Test
        @DisplayName("qualité augmente 2x plus vite après péremption")
        void quality_increases_twice_after_sell_date() {
            Item[] items = createItems(new Item("Aged Brie", 0, 20));
            updateQuality(items);
            assertEquals(22, items[0].quality);
        }

        @Test
        @DisplayName("qualité ne dépasse jamais 50")
        void quality_never_exceeds_50() {
            Item[] items = createItems(new Item("Aged Brie", 10, 50));
            updateQuality(items);
            assertEquals(50, items[0].quality);
        }

        @Test
        @DisplayName("qualité ne dépasse jamais 50 même après péremption")
        void quality_never_exceeds_50_after_sell_date() {
            Item[] items = createItems(new Item("Aged Brie", -1, 49));
            updateQuality(items);
            assertEquals(50, items[0].quality);
        }

    }
}
