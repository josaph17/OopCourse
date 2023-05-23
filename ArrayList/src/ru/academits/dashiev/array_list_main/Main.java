package ru.academits.dashiev.array_list_main;

import ru.academits.dashiev.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        System.out.println("list size = " + list1.size());

        ArrayList<Integer> list2 = new ArrayList<>(5);
        System.out.println("list2 size = " + list2.size());

        list1.add(0, 1);
        list1.add(2);
        list1.add(3);
        list1.add(0, 37);
        list1.add(4);
        list1.add(2, 3);

        list2.add(50);
        list2.add(6);

        System.out.println("list1: " + list1);
        System.out.println("list1 size = " + list1.size());
        System.out.println("list2: " + list2);
        System.out.println("list2 size = " + list2.size());

        list1.addAll(list2);
        System.out.println("list1.addAll(list2), list1: " + list1);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(43);
        list3.add(32);
        System.out.println("list3: " + list3);

        list1.addAll(2, list3);
        System.out.println("list1.addAll(index,list3), list1: " + list1);

        list3.clear();
        System.out.println("list3.clear(), list3: " + list3);
        System.out.println("list3 Size: " + list3.size());

        System.out.println("list1.contains(3777.9): " + list1.contains(37));

        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(67);
        list4.add(4);
        list4.add(1);
        System.out.println("list1: " + list1);
        System.out.println("list4: " + list4);
        System.out.println("list1.containsAll(list4): " + list1.containsAll(list4));

        int ensureCapacity = 5;
        list4.ensureCapacity(ensureCapacity);
        System.out.println("list4.ensureCapacity(" + ensureCapacity + "), list4: " + list4);

        int getIndex = 1;
        System.out.println("list4.get(index) = " + list4.get(getIndex));

        System.out.println("list1.hashCode() = " + list1.hashCode());

        int findIndex = 3;
        System.out.println("list1: " + list1);
        System.out.println("list1.indexOf(" + findIndex + "): " + list1.indexOf(findIndex));

        ArrayList<Double> emptyList = new ArrayList<>();
        System.out.println("emptyList.isEmpty(): " + emptyList.isEmpty());

        int lastIndexElement = 37;
        list1.add(lastIndexElement);
        System.out.println("list1 after .add(" + lastIndexElement + "): " + list1);
        System.out.println("list1.lastIndexOf(" + lastIndexElement + ") = " + list1.lastIndexOf(lastIndexElement));

        int removeIndexElement = 8;
        System.out.println(list1);
        list1.remove(removeIndexElement);
        System.out.println("list1 after .remove():");
        System.out.println(list1);

        ArrayList<Integer> removedList = new ArrayList<>();
        removedList.add(1);
        removedList.add(37);
        removedList.add(2);
        removedList.add(66);
        removedList.add(4);

        System.out.println("removedList: " + removedList);
        list1.removeAll(removedList);
        System.out.println("list1 after .removeAll(removedList): " + list1);

        // TODO прошу поверить ф-ю retainAll(Collection c)
        System.out.println("-----------------------");
        ArrayList<Integer> list5 = new ArrayList<>();
        list5.add(432);
        list5.add(32);
        list5.add(9);
        list5.add(564);
        list5.add(9);

        System.out.println("list5: " + list5);

        ArrayList<Integer> list6 = new ArrayList<>();
        list6.add(564);
        list6.add(9);
        list6.add(99);
        list6.add(32);

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
        Integer[] array2 = {2, 543, 43, 321, 0, 21, 7777};

        for (Integer element : array2) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.print("[] array = list1.toArray(array2): ");
        Integer[] array = list1.toArray(array2);

        for (Integer element : array) {
            System.out.print(element + " ");
        }

        System.out.println();

        list1.trimToSize();

        System.out.println(list6);
    }
}