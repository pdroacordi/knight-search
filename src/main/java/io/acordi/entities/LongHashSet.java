package io.acordi.entities;

import java.util.Arrays;

public class LongHashSet {
    private long[] table;
    private int capacity;
    private int size;
    private static final float LOAD_FACTOR = 0.75f;

    public LongHashSet(int initialCapacity) {
        capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        table = new long[capacity];
        Arrays.fill(table, Long.MIN_VALUE);
    }

    public boolean add(long value) {
        if (contains(value))
            return false;
        if (size >= capacity * LOAD_FACTOR)
            rehash();
        int idx = indexFor(value);
        while (table[idx] != Long.MIN_VALUE) {
            idx = (idx + 1) & (capacity - 1);
        }
        table[idx] = value;
        size++;
        return true;
    }

    public boolean contains(long value) {
        int idx = indexFor(value);
        while (table[idx] != Long.MIN_VALUE) {
            if (table[idx] == value)
                return true;
            idx = (idx + 1) & (capacity - 1);
        }
        return false;
    }

    private int indexFor(long value) {
        return (int) (mix(value) & (capacity - 1));
    }

    private long mix(long value) {
        value ^= (value >>> 33);
        value *= 0xff51afd7ed558ccdL;
        value ^= (value >>> 33);
        value *= 0xc4ceb9fe1a85ec53L;
        value ^= (value >>> 33);
        return value;
    }

    private void rehash() {
        int newCapacity = capacity << 1;
        long[] newTable = new long[newCapacity];
        Arrays.fill(newTable, Long.MIN_VALUE);
        for (int i = 0; i < capacity; i++) {
            if (table[i] != Long.MIN_VALUE) {
                long value = table[i];
                int idx = (int) (mix(value) & (newCapacity - 1));
                while (newTable[idx] != Long.MIN_VALUE) {
                    idx = (idx + 1) & (newCapacity - 1);
                }
                newTable[idx] = value;
            }
        }
        capacity = newCapacity;
        table = newTable;
    }
}
