package com.example.algorithm

import com.example.algorithm.Algorithm.duplicate

fun main() {
    duplicate(intArrayOf(2, 3, 1, 0, 2, 5))
}

object Algorithm {

    /**
     * 要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
     *
     * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
     * 在调整过程中，如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。
     */
    fun duplicate(nums: IntArray): Int {
        for (i in nums.indices) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i]
                }
                swap(nums, i, nums[i])
            }
            swap(nums, i, nums[i])
        }
        return -1
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }

    /**
     * 要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。
     *
     * 该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。
     * 因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来快速地缩小查找区间，
     * 每次减少一行或者一列的元素。当前元素的查找区间为左下角的所有元素。
     */
    fun find(target: Int, matrix: Array<IntArray>): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return false
        }
        val rows: Int = matrix.size
        val cols: Int = matrix[0].size
        var r = 0
        var c = cols - 1 // 从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c]) {
                return true
            }
            if (target > matrix[r][c]) r++ else c--
        }
        return false
    }
}

