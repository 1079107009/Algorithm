package com.example.algorithm

import com.example.algorithm.Algorithm.duplicate

fun main() {
    duplicate(intArrayOf(2, 3, 1, 0, 2, 5))
}

object Algorithm {

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    fun reverse(x: Int): Int {
        var temp = x
        var rev = 0
        while (temp != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0
            }
            val digit = temp % 10
            temp /= 10
            rev = rev * 10 + digit
        }
        return rev
    }

    /**
     * 一层一层从外到里打印，观察可知每一层打印都有相同的处理步骤，唯一不同的是上下左右的边界不同了。
     * 因此使用四个变量 r1, r2, c1, c2 分别存储上下左右边界值，从而定义当前最外层。
     * 打印当前最外层的顺序：
     * 从左到右打印最上一行->从上到下打印最右一行->从右到左打印最下一行->从下到上打印最左一行。
     * 应当注意只有在 r1 != r2 时才打印最下一行，也就是在当前最外层的行数大于 1 时才打印最下一行，
     * 这是因为当前最外层只有一行时，继续打印最下一行，会导致重复打印。打印最左一行也要做同样处理。
     */
    fun printMatrix(matrix: Array<IntArray>): List<Int> {
        val result = arrayListOf<Int>()
        var r1 = 0
        var r2: Int = matrix.size - 1
        var c1 = 0
        var c2: Int = matrix[0].size - 1
        while (r1 <= r2 && c1 <= c2) {
            // 上
            for (i in c1..c2) {
                result.add(matrix[r1][i])
            }
            // 右
            for (i in r1 + 1..r2) {
                result.add(matrix[i][c2])
            }
            // 下
            if (r1 != r2) {
                for (i in c2 - 1 downTo c1) {
                    result.add(matrix[r2][i])
                }
            }
            // 左
            if (c1 != c2) {
                for (i in r2 - 1 downTo r1 + 1) {
                    result.add(matrix[i][c1])
                }
            }
            r1++
            r2--
            c1++
            c2--
        }
        return result
    }

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

