package ru.academits.dashiev.my_hash_table_main;

import ru.academits.dashiev.my_hash_table.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> hashTable = new MyHashTable<>(10);

        System.out.println(hashTable.size());

        hashTable.add(32);
    }
}
