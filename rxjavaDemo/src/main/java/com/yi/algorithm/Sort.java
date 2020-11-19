package com.yi.algorithm;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] nums = {2, 5, 7, 3, 8, 1};
//        bubbleSort(nums);
//        insertSort(nums);
//        ChoiceSort(nums);
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * HeapSort
     */
    private static void heapSort(int[] nums) {

    }

    /**
     * quickSort
     * 数列中跳出一个基准，大于基准的放前面，小于基准的放后面
     * 退出时该基准就是下次操作的
     * (n*n - nlogn) 1
     */
    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1);
            quickSort(nums, partitionIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * ChoiceSort
     * 每次遍历查找最大数，放在首位
     * vs 插入排序
     * n*n 1
     */
    private static void ChoiceSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 找到最大值放在队头
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[maxIndex];
            nums[maxIndex] = temp;
        }
    }

    /**
     * insert sort
     * 将无序部分插入到有序部分中
     * n*n 1
     */
    private static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (nums[j] > current) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = current;
        }
    }

    /**
     * Bubble sort
     * 依次遍历，大就交换
     * n*n 1
     */
    private static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
//        nums.forEachIndexed { i, value ->
//                nums.forEachIndexed { j, valu ->
//            if (j > i) {
//                if (nums[i] < nums[j]) {
//                    val temp = nums[i]
//                    nums[i] = nums[j]
//                    nums[j] = temp
//                }
//            }
//        }
//        }
    }
}
