/**
 * Created by Shreyash Patodia and Max Lee (Ho Suk Lee).
 * Student numbers: Shreyash - 767336, Max Lee - 719577
 * Login: Shreyash - spatodia, Max - hol2
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2017.
 */

package com.teammaxine.board.helpers;

import java.util.*;

/**
 * Implementation of LRU cache used as transposition table
 */
public class LRUCache<K, V> {
    private int capacity;
    private LinkedHashMap<K, V> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<K, V>();
    }

    /**
     * If key is found then make it the most recently used,
     * key i.e. bring it to the front of the hashmap.
     * @param key The key to be searched for.
     * @return The value returned.
     */
    public V get(K key) {
        V value = this.map.get(key);
        if(value != null) {
            this.set(key, value);
        }
        return value;
    }

    /**
     * Sets the value of a certain key, removes a the least
     * recently used key from cache if necessary i.e. if
     * the cache is at capacity.
     * @param key The key to be inserted
     * @param value The value associated with the key.
     */
    public void set(K key, V value) {
        if(map.containsKey(key)) {
            this.map.remove(key);
        }
        else if(map.size() == this.capacity) {
            Iterator<K> it = this.map.keySet().iterator();
            it.next();
            it.remove();
        }
        this.map.put(key, value);
        return;
    }
}
