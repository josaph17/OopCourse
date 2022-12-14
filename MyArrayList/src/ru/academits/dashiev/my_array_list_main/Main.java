package ru.academits.dashiev.my_array_list_main;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Double> list1 = new MyArrayList<>();
        System.out.println("list size = " + list1.size());

        MyArrayList<Double> list2 = new MyArrayList<>(5);
        System.out.println("list2 size = " + list2.size());

        list1.add(0, 1.1);
        list1.add(2.2);
        list1.add(3.3);
        list1.add(0, 3777.9);
        list1.add(4.4);
        list1.add(2, 3.09);

        list2.add(50.5);
        list2.add(66.7);

        System.out.println("list1: " + list1);
        System.out.println("list1 size = " + list1.size());
        System.out.println("list2: " + list2);
        System.out.println("list2 size = " + list2.size());

        list1.addAll(list2);
        System.out.println("list1.addAll(list2), list1: " + list1);

        MyArrayList<Double> list3 = new MyArrayList<>();
        list3.add(43.3);
        list3.add(32.9);
        System.out.println("list3: " + list3);

        list1.addAll(2, list3);
        System.out.println("list1.addAll(index,list3), list1: " + list1);

        list3.clear();
        System.out.println("list3.clear(), list3: " + list3);
        System.out.println("list3 Size: " + list3.size());

        System.out.println("list1.contains(3777.9): " + list1.contains(3777.9));

        MyArrayList<Double> list4 = new MyArrayList<>();
        list4.add(6789.9);
        list4.add(4.0);
        list4.add(1.5654);
        System.out.println("list1: " + list1);
        System.out.println("list4: " + list4);
        System.out.println("list1.containsAll(list4): " + list1.containsAll(list4));

        int ensureCapacity = 5;
        list4.ensureCapacity(ensureCapacity);
        System.out.println("list4.ensureCapacity(" + ensureCapacity + "), list4: " + list4);

        int getIndex = 1;
        System.out.println("list4.get(index) = " + list4.get(getIndex));

        int hash = list1.hashCode();
        System.out.println("list1.hashCode() = " + list1.hashCode());

        double findIndex = 3.09;
        System.out.println("list1: " + list1);
        System.out.println("list1.indexOf(" + findIndex + "): " + list1.indexOf(findIndex));

        MyArrayList<Double> emptyList = new MyArrayList<>();
        System.out.println("emptyList.isEmpty(): " + emptyList.isEmpty());

        double lastIndexElement = 3777.9;
        list1.add(lastIndexElement);
        System.out.println("list1 after .add(" + lastIndexElement + "): " + list1);
        System.out.println("list1.lastIndexOf(" + lastIndexElement + ") = " + list1.lastIndexOf(
                lastIndexElement));

        int removeIndexElement = 8;
        System.out.println(list1);
        Double removeElement = list1.remove(removeIndexElement);
        System.out.println("removeIndexElement(" + removeIndexElement + "), removeElement: " + removeElement);

        MyArrayList<Double> removeList = new MyArrayList<>();
        removeList.add(1.1);
        removeList.add(3777.9);
        removeList.add(2.2);
        removeList.add(66.7);
        removeList.add(4.4);

        System.out.println("removeList: " + removeList);
        list1.removeAll(removeList);
        System.out.println("list1 after .removeAll(removeList): " + list1);

        // TODO прошу поверить ф-ю retainAll(Collection c)
        System.out.println("-----------------------");
        MyArrayList<Double> list5 = new MyArrayList<>();
        list5.add(432.3);
        list5.add(32.4);
        list5.add(9.7);
        list5.add(564.65);
        list5.add(9.7);
        System.out.println("list5: " + list5);

        MyArrayList<Double> list6 = new MyArrayList<>();
        list6.add(564.65);
        list6.add(9.7);
        list6.add(99.3);
        list6.add(32.4);
        System.out.println("list6: " + list6);

        list5.retainAll(list6);
        System.out.println("list5 after list5.retainAll(list6): " + list5);
        System.out.println("-----------------------");

        System.out.print("list1.toArray(): ");
        Object[] array1 = list1.toArray();

        for (Object o : array1) {
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.println("list1: " + list1);
        System.out.print("array2: ");
        Double[] array2 = {2.43, 543.4, 43.65, 321.0, 0.0, 21.4, 7777.7};

        for (Double element : array2) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.print("Double[] afterToArray = list1.toArray(array2): ");
        Double[] afterToArray = list1.toArray(array2);

        for (Double element : afterToArray) {
            System.out.print(element + " ");
        }

        System.out.println();

        list1.trimToSize();

        System.out.println(list6);
    }
}