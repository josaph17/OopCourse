package ru.academits.dashiev.list_main;

import ru.academits.dashiev.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println("Дан список: " + list);
        System.out.println();

        System.out.println("getSize() - получение размера списка");
        System.out.println(list.getSize());
        System.out.println();

        System.out.println("getFirstElement() - получение значение первого элемента");
        System.out.println(list.getFirstElement());
        System.out.println();

        int index = 2;
        System.out.println("get(int index) - получение значения по указанному индексу");
        System.out.println("индекс = " + index);
        System.out.println(list.get(index));
        System.out.println();

        int value = 77;
        System.out.println("set(int index, T value) - изменение значения по указанному индексу");
        list.set(index, value);
        System.out.println("Элемент по индексу = " + index + ", значение = " + value);
        System.out.println(list);
        System.out.println();

        System.out.println("getFirstElement() - получение значения первого элемента");
        System.out.println(list.getFirstElement());
        System.out.println();

        System.out.println("remove() - удаление элемента по индексу");
        int deleteIndex = 1;
        System.out.println("Удаляем элемент по индексу = " + deleteIndex);
        list.remove(deleteIndex);
        System.out.println(list);
        System.out.println();

        System.out.println("add() - вставка элемента в начало");
        list.add(55);
        list.add(33);
        list.add(11);
        System.out.println(list);
        System.out.println();

        int addIndex = 3, addValue = 1007;
        System.out.println("addIndexElement(int index, T data) - вставка элемента по индексу" );
        System.out.println("Индекс = " + addIndex + ", значение = " + addValue);
        list.addIndexElement(addIndex, addValue);
        System.out.println(list);
        System.out.println();

        System.out.println(
                "removeValue(T data) - удаление узла по значению, пусть выдает true, если элемент был удален");
        int removeData = 1;
        System.out.println("Список до использования данной функции: " + list);
        System.out.println("Проверяем удален ли  узел со значением = " + removeData);
        System.out.println("Ответ: " + list.removeValue(removeData));
        System.out.println("Список после использования данной функции: " + list);
        System.out.println();

        System.out.println("removeFirst() - удаление первого элемента");
        list.removeFirst();
        System.out.println(list);
        System.out.println();

        System.out.println("reverse() - разворот списка за линейное время");
        list.reverse();
        System.out.println(list);
        System.out.println();

        System.out.println("Копирование списка через конструктор копирования  List<Integer> copyList = new List<>(list)");
        List<Integer> copyList = new List<>(list);
        System.out.println(copyList);
    }
}
