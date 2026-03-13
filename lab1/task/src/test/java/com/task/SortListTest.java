package com.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.task.SortList.mergeSort;
import static org.junit.jupiter.api.Assertions.*;

public class SortListTest {

    @Test
    @DisplayName("проверка метод по алгоритму слияния")
    public void testSortList_1() {
        int[] input = {3, 2, 4, 1, 5};
        int[] output = {1, 2, 3, 4, 5};

        mergeSort(input);

        assertArrayEquals(output, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если есть одинаковые чисел")
    void testSortList_2() {
        int[] input = {4, 2, 8, 2, 5, 4, 1};
        int[] output = {1, 2, 2, 4, 4, 5, 8};

        mergeSort(input);
        assertArrayEquals(output, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если они уже в порядке")
    void testSortList_3() {
        int[] input = {1, 2, 3, 4, 5};
        int[] output = {1, 2, 3, 4, 5};

        mergeSort(input);
        assertArrayEquals(output, input);
    }

    @Test
    @DisplayName("проверка метод по алгоритму слияния, если они в порядке убывания")
    void testSortList_4() {
        int[] input = {9, 7, 5, 3, 1};
        int[] output = {1, 3, 5, 7, 9};

        mergeSort(input);
        assertArrayEquals(output, input);
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
