package ru.academits.dashiev.my_hash_table_main;

import ru.academits.dashiev.my_hash_table.MyHashTable;

import java.util.Iterator;

public class Main {
    public static void showHashTable(MyHashTable hashTable) {
        Iterator newListIterator = hashTable.iterator();

        while (newListIterator.hasNext()) {
            System.out.print(newListIterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyHashTable<Integer> hashTable = new MyHashTable<>(15);

        hashTable.add(87);
        hashTable.add(111);
        hashTable.add(421);

        showHashTable(hashTable);

        System.out.println(hashTable.size());
        System.out.println(hashTable.contains(87));

        Object[] array = hashTable.toArray();

        for (Object o : array) { // выводим массив
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
