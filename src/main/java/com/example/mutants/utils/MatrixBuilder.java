package com.example.mutants.utils;

import java.util.List;

/**
 * Created by mnsantos on 10/2/17.
 */
public class MatrixBuilder {

    public static char[][] buildMatrix(List<String> list) {
        int size = list.size();
        char[][] charArray = new char[size][size];
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            charArray[i] = string.toCharArray();
        }
        return charArray;
    }

}
