package com.teammaxine.board.helpers;

import java.util.*;

/**
 * Created by shreyashpatodia on 15/05/17.
 */
public class LRUCache<K, V> {
    private int capacity;
    private LinkedHashMap<K, V> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<K, V>();
    }

    public V get(K key) {
        V value = this.map.get(key);
        if(value != null) {
            this.set(key, value);
        }
        return value;
    }

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
