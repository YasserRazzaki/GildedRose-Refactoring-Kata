package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // ==========================================
    // Méthodes Utilitaires pour les Tests
    // ==========================================
    private Item[] createItems(Item... items) {
        return items;
    }

    private void updateQuality(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    // ==========================================
    // Classes de Tests Intégrées (@Nested)
    // ==========================================

    @Nested
    @DisplayName("Tests - Sulfuras")
    class SulfurasTests {

        @Test
        @DisplayName("La qualité reste toujours à 80")
        void quality_remains_always_80() {
            Item[] items = createItems(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
            updateQuality(items);
            assertEquals(80, items[0].quality);
        }

        @Test
        @DisplayName("Le sellIn ne change jamais")
        void sellIn_never_changes() {
            Item[] items = createItems(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
            updateQuality(items);
            assertEquals(10, items[0].sellIn);
        }

        @Test
        @DisplayName("Même comportement immuable avec un sellIn initial négatif")
        void same_behavioras_as_precedent_tests_with_negative_sellIn() {
            Item[] items = createItems(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
            updateQuality(items);
            assertEquals(-1, items[0].sellIn);
            assertEquals(80, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Tests - Aged Brie")
    class AgedBrieTests {

        @Test
        @DisplayName("La qualité augmente avec le temps")
        void quality_increases_over_time() {
            Item[] items = createItems(new Item("Aged Brie", 10, 20));
            updateQuality(items);
            assertEquals(21, items[0].quality);
        }

        @Test
        @DisplayName("La qualité augmente 2x plus vite après la date de péremption")
        void quality_increases_twice_after_sell_date() {
            Item[] items = createItems(new Item("Aged Brie", 0, 20));
            updateQuality(items);
            assertEquals(22, items[0].quality);
        }

        @Test
        @DisplayName("La qualité ne dépasse jamais la borne maximale de 50")
        void quality_never_exceeds_50() {
            Item[] items = createItems(new Item("Aged Brie", 10, 50));
            updateQuality(items);
            assertEquals(50, items[0].quality);
        }

        @Test
        @DisplayName("La qualité ne dépasse jamais 50 même en état périmé")
        void quality_never_exceeds_50_after_sell_date() {
            Item[] items = createItems(new Item("Aged Brie", -1, 49));
            updateQuality(items);
            assertEquals(50, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Tests - Backstage passes")
    class BackstagePassesTests {

        @Test
        @DisplayName("La qualité augmente de 1 quand sellIn > 10")
        void quality_increases_by_1_when_sellIn_above_10() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
            updateQuality(items);
            assertEquals(21, items[0].quality);
        }

        @Test
        @DisplayName("La qualité augmente de 2 quand sellIn est entre 6 et 10")
        void quality_increases_by_2_when_sellIn_between_6_and_10() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
            updateQuality(items);
            assertEquals(22, items[0].quality);
        }

        @Test
        @DisplayName("La qualité augmente de 2 pile à la frontière sellIn = 6")
        void quality_increases_by_2_when_sellIn_is_6() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20));
            updateQuality(items);
            assertEquals(22, items[0].quality);
        }

        @Test
        @DisplayName("La qualité augmente de 1 à la frontière supérieure sellIn = 11")
        void backstage_passes_quality_increases_by_1_at_sell_in_11() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
            updateQuality(items);
            assertEquals(10, items[0].sellIn);
            assertEquals(21, items[0].quality);
        }

        @Test
        @DisplayName("La qualité augmente de 3 quand sellIn est entre 1 et 5")
        void quality_increases_by_3_when_sellIn_between_1_and_5() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
            updateQuality(items);
            assertEquals(23, items[0].quality);
        }

        @Test
        @DisplayName("La qualité tombe à 0 immédiatement après le concert")
        void quality_drops_to_0_after_concert() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
            updateQuality(items);
            assertEquals(0, items[0].quality);
        }

        @Test
        @DisplayName("La qualité se bloque à 50 et ne la dépasse jamais")
        void quality_never_exceeds_50() {
            Item[] items = createItems(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49));
            updateQuality(items);
            assertEquals(50, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Tests - Conjured Items")
    class ConjuredTests {

        @Test
        @DisplayName("La qualité diminue 2x plus vite qu'un item normal avant expiration")
        void conjured_quality_decreases_twice_as_fast() {
            Item[] items = createItems(new Item("Conjured Mana Cake", 10, 20));
            updateQuality(items);
            assertEquals(9, items[0].sellIn);
            assertEquals(18, items[0].quality);
        }

        @Test
        @DisplayName("La qualité diminue 4x plus vite après la date d'expiration")
        void conjured_quality_decreases_four_times_as_fast_after_expiring() {
            Item[] items = createItems(new Item("Conjured Mana Cake", 0, 20));
            updateQuality(items);
            assertEquals(-1, items[0].sellIn);
            assertEquals(16, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Tests - Items normaux")
    class NormalItems {

        @Test
        @DisplayName("La qualité et le sellIn diminuent de 1 chaque jour")
        void quality_and_sellIn_decrease_by_1() {
            Item[] items = createItems(new Item("Normal Item", 10, 20));
            updateQuality(items);
            assertEquals(9, items[0].sellIn);
            assertEquals(19, items[0].quality);
        }

        @Test
        @DisplayName("La qualité diminue 2x plus vite après la date d'expiration")
        void quality_decreases_twice_as_fast_after_sell_date() {
            Item[] items = createItems(new Item("Normal Item", 0, 20));
            updateQuality(items);
            assertEquals(-1, items[0].sellIn);
            assertEquals(18, items[0].quality);
        }

        @Test
        @DisplayName("La qualité n'est jamais négative")
        void quality_never_negative() {
            Item[] items = createItems(new Item("Normal Item", 4, 0));
            updateQuality(items);
            assertEquals(0, items[0].quality);
        }

        @Test
        @DisplayName("La qualité n'est jamais négative même après expiration")
        void quality_never_negative_after_sell_date() {
            Item[] items = createItems(new Item("Normal Item", 0, 0));
            updateQuality(items);
            assertEquals(0, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Tests - Cas spéciaux")
    class SpecialCasesTests {

        @Test
        @DisplayName("Comportement correct d'un item normal avec un sellIn très négatif")
        void normal_item_with_very_negative_sellIn() {
            Item[] items = createItems(new Item("Normal", -10, 10));
            updateQuality(items);
            assertEquals(8, items[0].quality);
        }

        @Test
        @DisplayName("Mise à jour simultanée de plusieurs types d'items dans le tableau")
        void multiple_items_updated_together() {
            Item[] items = createItems(
                new Item("Normal Item", 10, 20),
                new Item("Aged Brie", 5, 30),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80)
            );
            updateQuality(items);
            assertEquals(19, items[0].quality);
            assertEquals(31, items[1].quality);
            assertEquals(80, items[2].quality);
        }
    }
}
