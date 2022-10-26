package super_test;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Testing {
    public static void main(String[] args) {
        MyArrayList<Integer> list3 = new MyArrayList<>();
        list3.add(1);
        list3.add(3);
        list3.add(4);
        list3.add(3);
        list3.add(1);
        list3.add(45);
        list3.add(88);
        list3.add(4);
        list3.add(3);
        MyArrayList<Integer> list4 = new MyArrayList<>();
        list4.add(4);
        list4.add(2);
        list4.add(3);
        list4.add(3);

        list3.retainAll(list4);
        System.out.println(list3);
    }

}
