package com.teammaxine.board.helpers;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public abstract interface Compresser<K, V> {
    public abstract V compress(K k);
}
