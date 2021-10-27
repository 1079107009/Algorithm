package com.example.algorithm

import com.example.algorithm.Algorithm.searchInsert
import java.util.*
import kotlin.collections.HashMap

fun main() {
    searchInsert(intArrayOf(1, 3, 5, 6), 2)
}

object Algorithm {

    /**
     * 搜索插入位置
     *
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        val size = nums.size
        var left = 0
        var right = size - 1
        var ans = size
        while (left <= right) {
            val mid = ((right - left) / 2) + left
            if (target <= nums[mid]) {
                right = mid - 1
                ans = mid
            } else {
                left = mid + 1
            }
        }
        return ans
    }

    /**
     * 实现strStr()函数。
     *
     * 给你两个字符串haystack和needle，请你在haystack字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
     * 如果不存在，则返回-1 。
     */
    fun strStr(haystack: String, needle: String): Int {
        return haystack.indexOf(needle)
    }

    /**
     * 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    fun removeDuplicates(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) {
            return 0
        }
        var fast = 1
        var slow = 1
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast]
                ++slow
            }
            ++fast
        }
        return slow
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            if (nums[left] == `val`) {
                nums[left] = nums[right - 1]
                right--
            } else {
                left++
            }
        }
        return left
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) {
            return l2
        } else if (l2 == null) {
            return l1
        } else if (l1.`val` < l2.`val`) {
            l1.next = mergeTwoLists(l1.next, l2)
            return l1
        } else {
            l2.next = mergeTwoLists(l1, l2.next)
            return l2
        }
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    fun isValid(s: String): Boolean {
        val length = s.length
        if (length % 2 != 0) {
            return false
        }
        val map = HashMap<Char, Char>().apply {
            put(')', '(')
            put(']', '[')
            put('}', '{')
        }
        val stack = LinkedList<Char>()
        for (i in 0 until length) {
            val c = s[i]
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map[c]) {
                    return false
                }
                stack.pop()
            } else {
                stack.push(c)
            }
        }
        return stack.isEmpty()
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     */
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isNullOrEmpty()) {
            return ""
        }
        var prefix = strs[0]
        val count = strs.size
        for (i in 1 until count) {
            val s = strs[i]
            val length = Math.min(prefix.length, s.length)
            var index = 0
            while (index < length && prefix[index] == s[index]) {
                index++
            }
            prefix = s.substring(0, index)
            if (prefix.isEmpty()) {
                return prefix
            }
        }
        return prefix
    }

    /**
     * 给定一个罗马数字，将其转换成整数。
     */
    fun romanToInt(s: String): Int {
        val replace = s.replace("IV", "a")
            .replace("IX", "b")
            .replace("XL", "c")
            .replace("XC", "d")
            .replace("CD", "e")
            .replace("CM", "f")

        var result = 0
        for (i in replace.indices) {
            result += which(replace[i])
        }
        return result
    }

    private fun which(ch: Char): Int {
        when (ch) {
            'I' -> return 1
            'V' -> return 5
            'X' -> return 10
            'L' -> return 50
            'C' -> return 100
            'D' -> return 500
            'M' -> return 1000
            'a' -> return 4
            'b' -> return 9
            'c' -> return 40
            'd' -> return 90
            'e' -> return 400
            'f' -> return 900
        }
        return 0
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     */
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) {
            return false
        }
        var y = x
        var result = 0
        while (y != 0) {
            result = result * 10 + y % 10
            y /= 10
        }
        return result == x
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
     * 并返回它们的数组下标。
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { index, i ->
            if (map.containsKey(target - i)) {
                return intArrayOf(map[target - i]!!, index)
            }
            map[i] = index
        }
        return intArrayOf()
    }

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

