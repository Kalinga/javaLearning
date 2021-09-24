package de.ray.java.basics.excercise;

import java.util.Arrays;

public class SortNumbers {
    static int[] sort(int[] arr) {

        for(int i=0; i< arr.length+1; i++) {
            for(int j=i+1; j< arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            } // n + n-1 + n-2+..-+1 = O(n^2)

        }

        return arr;
    }

    static int[] merge(int[] arr1, int[]arr2) {
        int [] mergedArr = new int[arr1.length + arr2.length];
        int j=0;
        int k=0;

        for (int i=0; i<arr1.length && j<arr2.length; i++) {
            if (arr1[i] > arr2[j]) {
                //ArrayUtils.addAll(mergedArr, arr2);
                //mergedArr.app arr2[j].fo;
            } else
                mergedArr[k] = arr1[i];
            }


        return mergedArr;
    }


    public static void main(String[] args) {
        System.out.printf("%s", SortNumbers.sort(new int[]{2,3})); // I@6e3c1e69
        System.out.println(Arrays.toString(SortNumbers.sort(new int[]{4,3,1,5,-9,12,104,0,154,02,3})));
    }
}
