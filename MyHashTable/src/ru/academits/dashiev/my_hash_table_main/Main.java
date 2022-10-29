package ru.academits.dashiev.my_hash_table_main;

import ru.academits.dashiev.my_hash_table.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void showHashTable(MyHashTable<Integer> hashTable) {
        if (hashTable.size() == 0) {
            System.out.println("[]");
            return;
        }

        Iterator newListIterator = hashTable.iterator();

        while (newListIterator.hasNext()) {
            System.out.print(newListIterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>(15);

        Integer addElement = 421;

        hashTable1.add(87);
        hashTable1.add(111);
        System.out.println("hashTable1.add(" + addElement + "): " + hashTable1.add(addElement));

        showHashTable(hashTable1);

        System.out.println("hashTable1 size = " + hashTable1.size());

        ArrayList arrayList1 = new ArrayList<>(Arrays.asList(789, 233));

        hashTable1.addAll(arrayList1);
        System.out.print("hashTable1.addAll(arrayList1), hashTable1: ");
        showHashTable(hashTable1);

        hashTable1.clear();
        System.out.print("hashTable1 after hashTable1.clear(): ");
        showHashTable(hashTable1);

        hashTable1.add(7);
        hashTable1.add(190);
        hashTable1.add(778);
        System.out.print("hashTable1: ");
        showHashTable(hashTable1);


        Integer containsObject = 778;
        System.out.println("hashTable1.contains(" + containsObject + "): " + hashTable1.contains(
                containsObject));

        LinkedList<Integer> containsLinkedList = new LinkedList<>(Arrays.asList(7, 190, 777));
        System.out.println("containsLinkedList: " + containsLinkedList);
        System.out.println("hashTable1.containsAll(containsLinkedList): " + hashTable1.containsAll(
                containsLinkedList));

        System.out.println("hashTable1.isEmpty(): " + hashTable1.isEmpty());

        System.out.println("hashTable1.iterator() реализован в ф-ии showHashTable");

        System.out.print("Object[] array = hashTable1.toArray(), array: ");
        Object[] array = hashTable1.toArray();
        for (Object o : array) { // выводим массив
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.print("testToArray = hashTable1.toArray(testToArray),tesToArray: ");
        Integer[] testToArray = new Integer[]{1};
        testToArray = hashTable1.toArray(testToArray);
        for (Integer integer : testToArray) {
            System.out.print(integer + " ");
        }
        System.out.println();

        System.out.println("-------Не знаю как проверять ф-ии remove, removeAll, retainAll-------");
        Integer removeElement = 190;
        hashTable1.remove(removeElement);

        MyHashTable<Integer> hashTable2 = new MyHashTable<>(25);
        hashTable2.add(543);
        hashTable2.add(1);
        hashTable2.add(432);
        hashTable2.add(17);
        hashTable2.add(8);
        hashTable2.add(101);
        hashTable2.add(54353);
        LinkedList<Integer> removeAll = new LinkedList<>(Arrays.asList(54353, 543, 7, 8, 1, 78));
        hashTable2.removeAll(removeAll);

        MyHashTable<Integer> hashTable3 = new MyHashTable<>(20);
        hashTable3.add(78);
        hashTable3.add(6545);
        hashTable3.add(54);
        hashTable3.add(1);
        hashTable3.add(890);
        LinkedList<Integer> retainLinkedList = new LinkedList<>(Arrays.asList(78, 44, 1));
        hashTable3.retainAll(retainLinkedList);
    }
}
