package ru.academits.dashiev.hash_table_main;

import ru.academits.dashiev.hash_table.HashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>(15);

        Integer elementToAdd = 421;

        System.out.println("hashTable1.add(" + elementToAdd + "): " + hashTable1.add(elementToAdd));

        Integer elementToAdd2 = 87;
        Integer elementToAdd3 = 111;

        hashTable1.add(elementToAdd2);
        hashTable1.add(elementToAdd3);

        System.out.println("hashTable1 after add elements "+ elementToAdd2 + ", " + elementToAdd3 + ": " + hashTable1);

        System.out.println("hashTable1 size = " + hashTable1.size());

        List<Integer> list1 = new ArrayList<>(Arrays.asList(789, 233));

        System.out.println("list1: " + list1);

        hashTable1.addAll(list1);
        System.out.println("hashTable1.addAll(list1), hashTable1: " + hashTable1);

        hashTable1.clear();
        System.out.println("hashTable1 after hashTable1.clear(): " + hashTable1);

        Integer elementToAdd4 = 7;
        Integer elementToAdd5 = 190;
        Integer elementToAdd6 = 778;

        hashTable1.add(elementToAdd4);
        hashTable1.add(elementToAdd5);
        hashTable1.add(elementToAdd6);
        System.out.println("hashTable1 after add "+ elementToAdd4 + ", " + elementToAdd5 +", " + elementToAdd6 +": " + hashTable1);

        Integer objectForCheckToContain = 778;

        System.out.println("hashTable1.contains(" + objectForCheckToContain + "): " + hashTable1.contains(objectForCheckToContain));

        LinkedList<Integer> containsLinkedList = new LinkedList<>(Arrays.asList(7, 190, 777));

        System.out.println("containsLinkedList: " + containsLinkedList);
        System.out.println("hashTable1.containsAll(containsLinkedList): " + hashTable1.containsAll(containsLinkedList));

        System.out.println("hashTable1.isEmpty(): " + hashTable1.isEmpty());

        System.out.println("hashTable1.iterator() реализован в ф-ии showHashTable");

        System.out.print("Object[] array = hashTable1.toArray(), array: ");
        Object[] array = hashTable1.toArray();

        for (Object o : array) { // выводим массив
            System.out.print(o + " ");
        }

        System.out.println();

        System.out.print("arrayToTest = hashTable1.toArray(arrayToTest),arrayToTest: ");

        Integer[] arrayToTest = {1};

        arrayToTest = hashTable1.toArray(arrayToTest);

        for (Integer integer : arrayToTest) {
            System.out.print(integer + " ");
        }

        System.out.println();

        Integer elementToRemove = 190;

        hashTable1.remove(elementToRemove);

        HashTable<Integer> hashTable2 = new HashTable<>(25);

        hashTable2.add(543);
        hashTable2.add(1);
        hashTable2.add(432);
        hashTable2.add(17);
        hashTable2.add(8);
        hashTable2.add(101);
        hashTable2.add(54353);

        LinkedList<Integer> listForRemoveAll = new LinkedList<>(Arrays.asList(54353, 543, 7, 8, 1, 78));

        System.out.println("HashTable2 before removeAll: " + hashTable2);
        System.out.println("listForRemoveAll: " + listForRemoveAll);

        hashTable2.removeAll(listForRemoveAll);

        System.out.println("HashTable2 after removeAll: " + hashTable2);

        HashTable<Integer> hashTable3 = new HashTable<>(20);
        hashTable3.add(78);
        hashTable3.add(6545);
        hashTable3.add(54);
        hashTable3.add(1);
        hashTable3.add(890);

        LinkedList<Integer> retainLinkedList = new LinkedList<>(Arrays.asList(78, 44, 1));

        System.out.println("HashTable3 before retainAll: " + hashTable3);
        System.out.println("retainLinkedList: " + retainLinkedList);

        hashTable3.retainAll(retainLinkedList);

        System.out.println("HashTable3 after retainAll: " + hashTable3);
    }
}
