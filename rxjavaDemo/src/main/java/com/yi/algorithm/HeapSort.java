package com.yi.algorithm;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] array) {
        // 1.把无序数组构建成二叉堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2.循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下沉调整最大堆
            downAdjust(array, 0, i);
        }
    }

    /**
     * 下沉调整
     *
     * @param array
     * @param parentIndex
     * @param lenght
     */
    public static void downAdjust(int[] array, int parentIndex, int lenght) {
        // temp保存父节点值，用域最后得赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < lenght) {
            // 如果有右孩子，且右孩子小于左孩子得值，则定位到右孩子
            if (childIndex + 1 < lenght && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，直接跳出
            if (temp < array[childIndex]) {
                break;
            }
            // 无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 上浮调整
     */
    private static void upAdjust(int[] array) {
        int childIndex = array.length - 1;// 取队尾
        int parentIndex = (childIndex - 1) / 2;// 取堆顶
        // temp保存插入得叶子节点值，用域最后得赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            // 无需真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 构建堆
     */
    private static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，依次下沉调整
        for (int i = array.length / 2; i >= 0; i--) {
            downAdjust(array, i, array.length - 1);
        }
    }

    private void test() {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
