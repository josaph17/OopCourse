package super_test;

import java.util.ArrayList;
import java.util.Arrays;


public class Testing {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(1);
        list1.add(7);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(5);

        System.out.println(list1.removeAll(list2));
        System.out.println(list1);

//        ArrayList<Integer> list1 = new ArrayList<>();
    }

}
