package de.hdmstuttgart.parkspot.dummy;

import com.here.android.mpa.common.GeoCoordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This file is part of Parkspot.      
 *
 * Parkspot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation version 3 of the License.
 * Parkspot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Parkspot. 
 * If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2019, Hochschule der Medien
 * Author: Monika Grabke
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
        addItem(new DummyItem(String.valueOf(1), "Raspberry id798 \n", new GeoCoordinate(48.943394, 9.432170)));
        addItem(new DummyItem(String.valueOf(2), "Raspberry id547 \n", new GeoCoordinate(48.782897, 9.181490)));
        addItem(new DummyItem(String.valueOf(3), "Raspberry id272 \n", new GeoCoordinate(48.787061, 9.181640)));
        addItem(new DummyItem(String.valueOf(4), "Raspberry id159 \n", new GeoCoordinate(48.786838, 9.175194)));
        addItem(new DummyItem(String.valueOf(5), "Raspberry id820 \n", new GeoCoordinate(48.748388, 9.110238)));
        addItem(new DummyItem(String.valueOf(6), "Raspberry id435 \n", new GeoCoordinate(48.747141, 9.102149)));
        addItem(new DummyItem(String.valueOf(7), "Raspberry id242 \n", new GeoCoordinate(48.746032, 9.116121)));
        addItem(new DummyItem(String.valueOf(8), "Raspberry id246 \n", new GeoCoordinate(48.745496, 9.109608)));
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
        public final GeoCoordinate coordinate;

        public DummyItem(String id, String content, GeoCoordinate coordinate) {
            this.id = id;
            this.content = content;
            this.coordinate = coordinate;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
