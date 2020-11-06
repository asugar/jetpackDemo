package com.yi.algorithm

/**
 * 归并排序
 * 时间复杂度nlogn
 */
class MergeSort {


}

fun main() {
    val nums: Array<Int> = arrayOf(2, 1, 6, 3, 5, 4)
    mergeSort(nums, 0, nums.size - 1)
    println("main after sort ${nums.toList()}")
}

private fun mergeSort(nums: Array<Int>, left: Int, right: Int) {
    if (left == right) {
        return;
    } else {
        val center = (left + right) / 2
        println("mergeSort ${nums.toList()} -- $center")
        //左边的数不断进行拆分
        mergeSort(nums, left, center)
        //右边的数不断进行拆分
        mergeSort(nums, center + 1, right);
        //合并
        merge(nums, left, center + 1, right);
    }
}

private fun merge(arrays: Array<Int>, left: Int, center: Int, right: Int) {
    //左边的数组的大小
    val leftArray: Array<Int> = Array(center - left) { 0 }
    //右边的数组大小
    val rightArray: Array<Int> = Array(right - center + 1) { 0 }
    //往这两个数组填充数据
    for (i in left until center) {
        leftArray[i - left] = arrays[i];
    }
    for (i in center..right) {
        rightArray[i - center] = arrays[i];
    }

    var i = 0
    var j = 0
    // arrays数组的第一个元素
    var k: Int = left
    //比较这两个数组的值，哪个小，就往数组上放
    while (i < leftArray.size && j < rightArray.size) {
        //谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
        if (leftArray[i] < rightArray[j]) {
            arrays[k] = leftArray[i];
            i++
            k++
        } else {
            arrays[k] = rightArray[j];
            j++
            k++
        }
    }
    //如果左边的数组还没比较完，右边的数都已经完了，那么将左边的数抄到大数组中(剩下的都是大数字)
    while (i < leftArray.size) {
        arrays[k] = leftArray[i];
        i++
        k++
    }
    //如果右边的数组还没比较完，左边的数都已经完了，那么将右边的数抄到大数组中(剩下的都是大数字)
    while (j < rightArray.size) {
        arrays[k] = rightArray[j];
        k++
        j++
    }
    println("after sort ${arrays.toList()}")
}