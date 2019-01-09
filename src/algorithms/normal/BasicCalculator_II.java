package algorithms.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by kadokawa
 * Date 11:21 7/1/2019
 */
public class BasicCalculator_II {

    /*
        实现一个基本的计算器来计算一个简单的字符串表达式的值。
        字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

        示例 1:
        输入: "3+2*2"
        输出: 7

        示例 2:
        输入: " 3/2 "
        输出: 1

        示例 3:
        输入: " 3+5 / 2 "
        输出: 5

        说明：你可以假设所给定的表达式都是有效的。请不要使用内置的库函数 eval。

        <
            未完 倒在了最后一组测试用例 200k长度的算式文档上
            后续应对递归计算进行改动 判断符号前后 没有乘除 则可以进行加减计算
        >

     */

    public static void main(String[] args) {
//        String t = "1+2*3/4-1";
//        String t = "42";
        String t = "-1+23333*32/42";

        String b = "\"  30\"";
        String tt = "1*2+3*4";

        System.out.println(1*2+3*4);
        System.out.println(calculate(tt));
    }

    public static int calculate(String s) {
        s = s.replaceAll(" ","");
        s = s.replaceAll("\"","");
        s = s.replaceAll("\\\\","");
        List<String> list = new ArrayList<>();
        getString(s, list);

        if(!list.contains("*") &&!list.contains("/") &&!list.contains("+") &&!list.contains("-")){
            return Integer.valueOf(list.get(0));
        }

        Vector<String> v = new Vector<>();
        for (String s1 : list) {
            if(!"".equals(s1)){
                v.add(String.valueOf(s1));
            }
        }
        while (v.size()>1){
            calculateList(v);
        }

        int num = 0;
        for (String s1 : v) {
            if(s1 != ""){
                num = Integer.valueOf(s1);
            }
        }
        return num;
    }

    public static String getString(String b, List<String> numList){
        String add = "+", subtract = "-", multiply = "*", divide = "/";
        List<String> signList = new ArrayList<>();
        signList.add(add);
        signList.add(subtract);
        signList.add(multiply);
        signList.add(divide);


        char[] chars = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        String symbol = "";
        int size = 0;

        for (int i = 0; i < chars.length; i++) {
            size++;
            String val = String.valueOf(chars[i]);
            if(!signList.contains(val)){
                sb.append(val);
            }else{
                if(i == 0 && subtract.equals(val)){
                    sb.append(val);
                }else{
                    if (add.equals(val)) {
                        symbol = add;
                    }
                    if (subtract.equals(val)) {
                        symbol = subtract;
                    }
                    if (multiply.equals(val)) {
                        symbol = multiply;
                    }
                    if (divide.equals(val)) {
                        symbol = divide;
                    }
                    break;
                }
            }
        }
        if (sb.length() != 0) {
            numList.add(sb.toString());
        }
        if (symbol.length() != 0) {
            numList.add(symbol);
        }
        b = b.substring(size);
        if (b.length() != 0) {
            getString(b, numList);
        }
        return b;
    }

    public static Vector<String> calculateList(Vector<String> v){


        for (int i = 0; i < v.size(); i++) {
            if ("*".equals(v.get(i)) || "/".equals(v.get(i))) {
                if("*".equals(v.get(i))){
                    v.set(i, String.valueOf(Integer.valueOf(v.get(i - 1)) * Integer.valueOf(v.get(i + 1))));
                    v.remove(i + 1);
                    v.remove(i - 1);
                    return v;
                }
                if ("/".equals(v.get(i))) {
                    v.set(i, String.valueOf(Integer.valueOf(v.get(i - 1)) / Integer.valueOf(v.get(i + 1))));
                    v.remove(i + 1);
                    v.remove(i - 1);
                    return v;
                }
            }
        }

        for (int i = 0; i < v.size(); i++) {
            if ("+".equals(v.get(i)) || "-".equals(v.get(i))) {
                if("+".equals(v.get(i))){
                    v.set(i, String.valueOf(Integer.valueOf(v.get(i - 1)) + Integer.valueOf(v.get(i + 1))));
                    v.remove(i + 1);
                    v.remove(i - 1);
                    return v;
                }
                if("-".equals(v.get(i))){
                    v.set(i, String.valueOf(Integer.valueOf(v.get(i - 1)) - Integer.valueOf(v.get(i + 1))));
                    v.remove(i + 1);
                    v.remove(i - 1);
                    return v;
                }
            }
        }
        return v;
    }
}
