package algorithms.easy.scrap;

import java.util.*;

/**
 * Created by kadokawa
 * Date 16:47 7/1/2019
 */
public class Minimum_Index_Sum_of_Two_Lists {

    /*
        假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
        你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

        示例 1:
        输入:
        ["Shogun", "Tapioca Express", "Burger King", "KFC"]
        ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
        输出: ["Shogun"]
        解释: 他们唯一共同喜爱的餐厅是“Shogun”。

        示例 2:
        输入:
        ["Shogun", "Tapioca Express", "Burger King", "KFC"]
        ["KFC", "Shogun", "Burger King"]
        输出: ["Shogun"]
        解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。

        提示:
        两个列表的长度范围都在 [1, 1000]内。
        两个列表中的字符串的长度将在[1，30]的范围内。
        下标从0开始，到列表的长度减1。
        两个列表都没有重复的元素。
     */

    public static void main(String[] args) {
    //  String[] list1 = new String[]{"k","KFC"};
    //  String[] list2 = new String[]{"k","KFC"};
        String[] list1 = new String[]{"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = new String[]{"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"};

        System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }

    private static String[] findRestaurant(String[] list1, String[] list2) {
        // 此处使用了 Integer.MAX_VALUE 作为比较的初始值 则只要是第一次进入比较判断 都会进入 赋值cacheCount 以及 设置返回集合的初始内容中
        // 亦可使用多一个字段去记录 是否更改过比较的初始值。    <初始值直接取0的话，可能出现统计中的最小索引值也为0的情况>
        int cacheCount = Integer.MAX_VALUE;
        Vector<String> record = new Vector<>();
        Map<String,Integer> map = new HashMap<>();

        for (int i = 0; i < list1.length; i++) map.put(list1[i],i);

        for (int i = 0; i < list2.length && i <= cacheCount; i++) {
            if (map.containsKey(list2[i])) {
                int count = map.get(list2[i]) + i;
                if (count < cacheCount) {
                    record.clear();
                    record.add(list2[i]);
                    cacheCount = count;
                } else if (count == cacheCount) {
                    record.add(list2[i]);
                }

            }
        }
        return record.toArray(new String[0]);
    }
}
