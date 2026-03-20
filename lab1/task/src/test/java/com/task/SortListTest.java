package com.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static com.task.SortList.mergeSort;
import static org.junit.jupiter.api.Assertions.*;

public class SortListTest {

    @Test
    @DisplayName("проверка метод по алгоритму слияния")
    public void testSortList_1() {
        int[] input = {3, 2, 4, 1, 5};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если есть одинаковые чисел")
    void testSortList_2() {
        int[] input = {4, 2, 8, 2, 5, 4, 1};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если они уже в порядке")
    void testSortList_3() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если они в порядке убывания")
    void testSortList_4() {
        int[] input = {9, 7, 5, 3, 1};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если только 1 элемент")
    void testSortList_5() {
        int[] input = {23};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если только 2 элемент")
    void testSortList_6() {
        int[] input = {15, 21};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если внутри есть мах или мин")
    void testSortList_7() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если с большими количествами")
    void testSortList_LargeData() {
        int size = 10000;
        int[] input = new int[size];
        int[] expected = new int[size];

        Random random = new Random(10);

        for (int i = 0; i < size; i++) {
            int value = random.nextInt();
            input[i] = value;
            expected[i] = value;
        }

        Arrays.sort(expected);
        mergeSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("проверка вывод ошибка на метод по алгоритму слияния")
    void testSortList_Error1() {
        int[] input = new int[0];

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> mergeSort(input)
        );
        assertEquals("Ошибка: длина списка должна быть больше 0", exception.getMessage());
    }

    @Test
    @DisplayName("проверка вывод ошибка на метод по алгоритму слияния")
    void testSortList_Error2() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> mergeSort(null)
        );
        assertEquals("Ошибка: длина списка должна быть больше 0", exception.getMessage());
    }

}
