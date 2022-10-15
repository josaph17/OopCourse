package ru.academits.dashiev.my_list_main;

import ru.academits.dashiev.my_list.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println("��� ������: " + list);
        System.out.println();

        System.out.println("getSize() - ��������� ������� ������");
        System.out.println(list.getSize());
        System.out.println();

        System.out.println("getFirstElement() - ��������� �������� ������� ��������");
        System.out.println(list.getFirstElement());
        System.out.println();

        int index = 2;
        System.out.println("get(int index) - ��������� �������� �� ���������� �������");
        System.out.println("������ = " + index);
        System.out.println(list.get(index));
        System.out.println();

        int value = 77;
        System.out.println("������: " + list);
        System.out.println("set(int index, T value) - ��������� �������� �� ���������� �������");
        System.out.println(
                "� �������� �� ������� = " + index + " ������ �������� = " + list.set(index,
                                                                                      value));
        System.out.println("�� ����� �������� = " + value);
        System.out.println(list);
        System.out.println();

        System.out.println("getFirstElement() - ��������� �������� ������� ��������");
        System.out.println(list.getFirstElement());
        System.out.println();

        //TODO ���������
        System.out.println("������: " + list);
        System.out.println("remove(int index) - �������� �������� �� �������");
        int deleteIndex = 1;
        System.out.println("������� ������� �� ������� = " + deleteIndex);
        System.out.println("������ ������� �� ��������� = " + list.remove(deleteIndex));
        System.out.println(list);
        System.out.println();

        System.out.println("add() - ������� �������� � ������");
        list.add(55);
        list.add(33);
        list.add(11);
        System.out.println(list);
        System.out.println();

        int addIndex = 3, addValue = 1007;
        System.out.println("addIndexElement(int index, T data) - ������� �������� �� �������");
        System.out.println("������ = " + addIndex + ", �������� = " + addValue);
        list.addIndexElement(addIndex, addValue);
        System.out.println(list);
        System.out.println();

        System.out.println(
                "removeValue(T data) - �������� ���� �� ��������, ����� ������ true, ���� ������� ��� ������");
        int removeData = 1;
        System.out.println("������ �� ������������� ������ �������: " + list);
        System.out.println("��������� ������ ��  ���� �� ��������� = " + removeData);
        System.out.println("�����: " + list.removeValue(removeData));
        System.out.println("������ ����� ������������� ������ �������: " + list);
        System.out.println();

        System.out.println("removeFirst() - �������� ������� ��������");
        System.out.println("��� ������ ������� �� ��������� =  " + list.removeFirst());
        System.out.println(list);
        System.out.println();

        System.out.println("reverse() - �������� ������ �� �������� �����");
        list.reverse();
        System.out.println(list);
        System.out.println();

        System.out.println(
                "����������� ������ ����� ����������� �����������  List<Integer> copyList = new List<>(list)");
        MyList<Integer> copyList = new MyList<>(list);
        System.out.println(copyList);
    }
}
