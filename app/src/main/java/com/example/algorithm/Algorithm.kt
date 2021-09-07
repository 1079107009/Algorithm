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

}

