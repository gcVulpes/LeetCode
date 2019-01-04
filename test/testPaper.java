import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by kadokawa
 * Date 11:28 4/1/2019
 */
public class testPaper {

    public static void main(String[] args) {
        int a = 9;

        HashSet<Integer> set = new HashSet<>();


        set.add(a);

        if(set.contains(9)){
            System.out.println(Arrays.toString(set.toArray()));
        }else{
            System.out.println("没有");

        }


    }
}
