package super_test;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Testing {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(1);
        list1.add(7);

        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(1);
        list2.add(5);

        System.out.println(list1.removeAll(list2));
        System.out.println(list1);

//        ArrayList<Integer> list1 = new ArrayList<>();
    }

}
