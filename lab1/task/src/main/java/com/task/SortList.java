package com.task;

public class SortList {
    public static void mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Ошибка: длина списка должнен быть больше 0");
        }
        if (array.length == 1) {
            return;
        }
        sortList(array, 0, array.length - 1);
    }

    private static void sortList(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l)/2;
        sortList(arr, l, m);
        sortList(arr, m+1, r);

        int[] temp = new int[r - l + 1];
        int i = l;
        int j = m + 1;
        int k = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
                k++;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }
        while (i <= m) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= r) {
            temp[k] = arr[j];
            j++;
            k++;
        }
        System.arraycopy(temp, 0, arr, l, temp.length);
    }
}
