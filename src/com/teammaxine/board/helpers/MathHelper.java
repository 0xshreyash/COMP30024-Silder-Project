package com.teammaxine.board.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by noxm on 10/05/17.
 */
public class MathHelper {
    public static Integer[] cutArray(Integer[] arr, int start, int size) {
        Integer[] temp = new Integer[size];
        for(int i = start; i < start + size; i++) {
            temp[i - start] = arr[i];
        }
        return temp;
    }

    // http://stackoverflow.com/questions/2920315/permutation-of-array
    private static void permute(List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1);
            Collections.swap(arr, k, i);
        }

        if (k == arr.size() - 1){
            System.out.println(java.util.Arrays.toString(arr.toArray()));
        }
    }

    // http://stackoverflow.com/questions/5162254/all-possible-combinations-of-an-array
    public static <T> List<List<T>> combination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.<T> emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<T>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }

        combination.addAll(combination(subSet, size));

        return combination;
    }

    public static int factorial(int x) {
        int i = 1;
        while(x > 1) {
            i *= x;
            x--;
        }
        return i;
    }

    public static double choose(int x, int y) {
        // from http://stackoverflow.com/questions/1678690/what-is-a-good-way-to-implement-choose-notation-in-java
        // by mob

        if (y < 0 || y > x) return 0;
        if (y > x/2) {
            // choose(n,k) == choose(n,n-k),
            // so this could save a little effort
            y = x - y;
        }

        double denominator = 1.0, numerator = 1.0;
        for (int i = 1; i <= y; i++) {
            denominator *= i;
            numerator *= (x + 1 - i);
        }
        return numerator / denominator;
    }
}
