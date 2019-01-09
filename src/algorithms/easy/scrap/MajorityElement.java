package algorithms.easy.scrap;

import org.junit.Test;

import java.util.HashSet;
import java.util.Vector;

/**
 * Created by kadokawa
 * Date 10:24 4/1/2019
 */
public class MajorityElement {

    /*
        给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
        你可以假设数组是非空的，并且给定的数组总是存在众数。

        示例 1:
        输入: [3,2,3]
        输出: 3

        示例 2:
        输入: [2,2,1,1,1,2,2]
        输出: 2
     */

    public static void main(String[] args) {
        int[] nums = new int[]{8,8,7,7,7};
        System.out.println(majorityElement(nums));
        // 最佳
        // System.out.println(majorityElementBest(nums));
    }

    private static int majorityElement(int[] nums) {
        int numLength = nums.length / 2;
        HashSet set = new HashSet();
        for (int num1 : nums) {
            int count = 0;
            if (!set.contains(num1)) {
                boolean add = set.add(num1);
                if (add) {
                    for (int num : nums) {
                        if (num == num1) count++;
                    }
                    if (count > numLength) return num1;
                }
            }
        }
        return 0;
    }

    /*
        摩尔投票法
        摩尔投票算法可以快速的计算出一个数组中

        ！！ 出现次数过半 ！！

        的数，即大多数（majority)   <注意：仅适用于取单个众数 则数组中仅有一个数出现次数大于数组长度的一半>

        算法核心思想是同加，异减。

        举例
        假设目标数组为 [1,2,1,1,2,1,2]
        取第一位数为起始 记为 maj ，同时记录出现次数 1
        从数组第二位开始进入循环，
        当取出的数与 maj 相等时，则记录数 ++
        当取出的数与 maj 不同时，则记录数 --

        当记录数大于0时，则表示 当前的 maj仍为数组中的 majority(大多数)
          记录数为0，则表示 当前数 预选的 maj 已不是数组中的 majority
          取当前数设为新的 maj，继续进行循环
     */
    public static int majorityElementBest(int[] nums) {
        int count = 1;
        int maj = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i])
                count++;
            else {
                count--;
                if (count == 0) {
                    maj = nums[i];
                }
            }
        }
        return maj;
    }

    /*
        延伸
        如何使用摩尔投票法取数组中出现次数大于 [n/3] 的元素 <即取多个众数>

        题设为 次数大于[3/n] 的众数，则最多取两个数，限定时间和空间复杂度，不可使用排序或哈希表
     */

    @Test
    public Vector<Integer> moreMajorityElement() {
        Vector<Integer> answerList = new Vector<>();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 3, 3};

        int n1 = 0, c1 = 0, n2 = 0, c2 = 0;
        for (Integer num : nums) {
            /*
                num为循环中取出的数，n1、n2为标记数,c1、c2为对应的次数
                分别将循环数与标记数作比对，相等时，相应次数递增
                当记录数为0时，则取当前数字作标记数
                当循环数与标记数都不相等 且对应的次数都不为0时，则次数都递减
             */
            if (num == n1) c1++;
            else if (num == n2) c2++;
            else if (c1 == 0) {n1 = num;c1 = 1;}
            else if (c2 == 0) {n2 = num;c2 = 1;}
            else c1--;c2--;

        }
        //  System.out.println("标记数a为"+n1+",出现次数为:"+c1);
        //  System.out.println("标记数b为"+n2+",出现次数为:"+c2);
        c1 = c2 = 0;

        /*
            已确定n1,n2为数组中出现次数最多的两数。验证该两数是否符合题设要求 次数大于[n/3]
         */
        for (int num : nums) {
            if (num == n1) c1++;
            else if (num == n2) c2++;
        }
        if (c1 > nums.length / 3) answerList.add(n1);
        if (c2 > nums.length / 3) answerList.add(n2);

        return answerList;
    }
}