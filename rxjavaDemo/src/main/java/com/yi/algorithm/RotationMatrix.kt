package com.yi.algorithm

/**
 * 旋转矩阵
 * 90度  -   a[i][j] = b[h-1-j][i]
 * 180度 -   a[i][j] = b[h-1-i][w-1-j]
 * 270度 -   a[i][j] = b[j][w-1-i]
 */
fun main() {
    val matrix = Array(3) { Array(3) { it + 1 } }
    printMatrix(matrix)

    val matrix2 = Array(matrix.size) {
        Array(matrix[0].size) { 0 }
    }
    matrix2.forEachIndexed { i, ints ->

        ints.forEachIndexed { j, value ->
            matrix2[i][j] = matrix[3 - 1 - j][i]
        }
    }
    println("-----------------------")
    printMatrix(matrix2)
}

private fun printMatrix(matrix: Array<Array<Int>>) {
    matrix.forEach {
        println(it.toList())
    }
}





