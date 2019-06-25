package de.hdmstuttgart.parkspot.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    private static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<>();

    static {
        // Add some sample items.
        addItem(new DummyItem(String.valueOf(1), "Raspberry id798 \nLatitude: 21.94563, Longitude: -164.24798"));
        addItem(new DummyItem(String.valueOf(2), "Raspberry id547 \nLatitude: 54.89209, Longitude: 52.47664"));
        addItem(new DummyItem(String.valueOf(3), "Raspberry id272 \nLatitude: -23.15056, Longitude: -171.56628"));
        addItem(new DummyItem(String.valueOf(4), "Raspberry id159 \nLatitude: -28.45507, Longitude: 133.15978"));
        addItem(new DummyItem(String.valueOf(5), "Raspberry id820 \nLatitude: 58.71674, Longitude: 129.53751"));
        addItem(new DummyItem(String.valueOf(6), "Raspberry id435 \nLatitude: -8.24891, Longitude: -108.56783"));
        addItem(new DummyItem(String.valueOf(7), "Raspberry id242 \nLatitude: -28.92105, Longitude: 77.98870"));
        addItem(new DummyItem(String.valueOf(8), "Raspberry id246 \nLatitude: 12.84797, Longitude: -153.63357"));
        addItem(new DummyItem(String.valueOf(9), "Raspberry id869 \nLatitude: 58.69578, Longitude: 57.04733"));
        addItem(new DummyItem(String.valueOf(10), "Raspberry idi22 \nLatitude: -7.49030, Longitude: 152.50843"));
        addItem(new DummyItem(String.valueOf(11), "Raspberry id829 \nLatitude: 50.70225, Longitude: 77.50784"));
        addItem(new DummyItem(String.valueOf(12), "Raspberry id564 \nLatitude: -43.97845, Longitude: 49.14345"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.valueOf(item.id), item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
