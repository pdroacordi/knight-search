package io.acordi.entities;

import java.util.Arrays;

public class LongLinkedHashSet {

    private static class Node {
        long value;
        Node next;

        Node(long value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node[] table;
    private int capacity;
    private int size;
    private static final float LOAD_FACTOR = 0.75f;

    public LongLinkedHashSet(int initialCapacity) {
        capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        table = new Node[capacity];
    }

    public boolean add(long value) {
        if (contains(value))
            return false;

        int idx = indexFor(value);
        table[idx] = new Node(value, table[idx]);  // Insert at head
        size++;

        return true;
    }

    public boolean contains(long value) {
        int idx = indexFor(value);
        Node current = table[idx];
        while (current != null) {
            if (current.value == value)
                return true;
            current = current.next;
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

    public int size() {
        return size;
    }
}
