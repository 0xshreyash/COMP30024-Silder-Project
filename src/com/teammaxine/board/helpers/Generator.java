package com.teammaxine.board.helpers;

import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;
import com.teammaxine.board.elements.Board;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by noxm on 5/05/17.
 */
public class Generator {
    // test
    public static void main(String[] args) {
        generateBoard(5,1,1,0);
    }

    private static ArrayList<Integer[]> generatePositionPermutations(int size, int total) {
        ArrayList<Integer> tempObj = new ArrayList<>();
        for(int i = 0; i < size * size; i++) {
            tempObj.add(i);
        }

        List<Set<Integer>> allSubsets = getSubsets(tempObj, total);
        ArrayList<Integer[]> allPermutations = new ArrayList<>();

        int factVal = MathHelper.factorial(total);
        for(int i = 0; i < allSubsets.size(); i++) {
            Set<Integer> combination = allSubsets.get(i);
            Integer[] combArr = combination.toArray(new Integer[combination.size()]);
            Permute permute = new Permute(combArr);

            for(int j = 0; j < factVal; j++) {
                Integer[] temp = ((Integer[]) permute.next()).clone();
//                printArray(temp);
                allPermutations.add(temp);
            }
        }

//        for(Integer[] a : allPermutations) {
//            printArray(a);
//        }

        return allPermutations;
    }

    public static void printArray(Object[] arr) {
        System.out.print("[ ");
        for(int k = 0; k < arr.length; k++) {
            System.out.print(arr[k].toString() + ", ");
        }
        System.out.print("]\n");
    }

    public static ArrayList<Board> generateBoard(int size, int Hcount, int Vcount, int Bcount) {
        ArrayList<Board> boards = new ArrayList<>();
        int total = Hcount + Vcount + Bcount;

        ArrayList<Integer[]> allPermutations = generatePositionPermutations(size, total);

//        for(Integer[] a : allPermutations) {
//            printArray(a);
//        }

        // check validity
        /*
        int sumCombinations = (int) MathHelper.choose(size * size, total) * MathHelper.factorial(total);
        System.out.println(allPermutations.size());
        System.out.println(sumCombinations);
        if(allPermutations.size() != sumCombinations) {
            System.out.println("ERROR");
            return null;
        }
        */

        for(int i = 0; i < allPermutations.size(); i++) {
            Integer[] posArr = allPermutations.get(i);
//            printArray(posArr);
            Integer[] Hpos = MathHelper.cutArray(posArr, 0, Hcount);
            Integer[] Vpos = MathHelper.cutArray(posArr, Hcount, Vcount);
            Integer[] Bpos = MathHelper.cutArray(posArr, Vcount + Hcount, Bcount);
            boards.add(createBoard(Hpos, Vpos, Bpos, size));
        }

        for(Board b : boards) {
            System.out.println(b);
        }
        return boards;
    }

    private static Board createBoard(Integer[] hpos, Integer[] vpos, Integer[] bpos, int size) {
        /*
        printArray(hpos);
        printArray(vpos);
        printArray(bpos);
        */
        String boardString = "";
        for(int i = 0; i < size*size; i++) {
            if(i != 0 && i % size == 0)
                boardString += "\n";

            if(inArray(hpos, i)) {
                boardString += "H";
            } else if (inArray(vpos, i)) {
                boardString += "V";
            } else if (inArray(bpos, i)) {
                boardString += "B";
            } else {
                boardString += "+";
            }
        }

        return Board.boardFromString(boardString);
    }

    private static boolean inArray(Integer[] arr, int i) {
        for(int a = 0; a < arr.length; a++) {
            if(i == arr[a])
                return true;
        }
        return false;
    }

    public static Board generateRandomBoard(int size) {
        String boardString = "";

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                double rand = Math.random() * 23;
                if(rand < 2) {
                    boardString += "H";
                } else if (rand < 4) {
                    boardString += "V";
                } else if(rand < 6) {
                    boardString += "B";
                } else {
                    boardString += "+";
                }
            }
            boardString += "\n";
        }

        Board b = Board.boardFromString(boardString);

        return b;
    }

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx+1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx+1, current, solution);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }
}
