package com.back;

import com.task.Tan;

import static com.task.SortList.mergeSort;

public class Main {
    public static void main(String[] args) {
        //task 1
        System.out.println("tan(0) = " + Tan.calculateTan(0, 2));
        System.out.println("tan(1) = " + Tan.calculateTan(0.5, 3));
        System.out.println("tan(2) = " + Tan.calculateTan(1, 4));

        //task 2
        int[] arr = {8, 3, 5, 4, 7, 6, 1, 2};
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}