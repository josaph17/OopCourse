package ru.academits.dashiev.my_array_list_main;

import ru.academits.dashiev.my_array_list.MyArrayList;

import java.util.Iterator;

public class Main {
    public static void showWithIterator(MyArrayList arrayList) {
        Iterator newListIterator = arrayList.iterator();

        while (newListIterator.hasNext()) {
            System.out.print(newListIterator.next() + " ");
        }
        System.out.println();
    }

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
        System.out.println("list2: " + list2);

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

        System.out.println();
        System.out.println(
                "public Iterator<T> iterator() мы переопределили, использование iterator покажем в следующем методе ");
        System.out.print("showWithIterator(MyArrayList arrayList): ");
        showWithIterator(list1);
        System.out.println();

    }
}
