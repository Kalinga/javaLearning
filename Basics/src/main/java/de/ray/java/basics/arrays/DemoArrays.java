package de.ray.java.basics.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DemoArrays {
    public static void main(String[] args) {
        //System.out.println(removeArrayElement(new int[] {4,3,1,3}, 3)); //2
        System.out.println(removeArrayElement(new int[] {1,2,3,5,6,3,5,5,4,3}, 3)); //
        }

    static int removeArrayElement(int[] arr, int num) {
        System.out.println(Arrays.toString(arr));
    // removeArrayElement([1,2,3,5,6,3,5,5,4,,3], 3): [1,2,5,6,5,5,4]
        int newArrayIndex=0;
        for (int i = 0; i < arr.length; i++){
            System.out.printf("%d,%d,%d\n",i, newArrayIndex, arr[i]);
            if(num != arr[i] ) {
                if (newArrayIndex < i) {
                    arr[newArrayIndex] = arr[i];
                }
                ++newArrayIndex;
            }
        }
        return newArrayIndex;
    }

    void demoArray() {
        String[] stringArr = {"Ilmenau", "Erfurt", "Jena"};
        String stringArrVar[] = {"Ilmenau", "Erfurt", "Jena"};


        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};

        // Variant 1
        for (int i = 0; i < stringArr.length; i++) {
            System.out.println(stringArr[i]);
        }

        for (int i = 0; i < stringArr.length; i++) {
            System.out.println(stringArr[i]);
        }

        // Variant 2
        for (String c:cars) {
            System.out.println(c);
        }

        int[] intArray = new int[10];
        int intArrayVar[] = new int[] {1,2};
    }

}
