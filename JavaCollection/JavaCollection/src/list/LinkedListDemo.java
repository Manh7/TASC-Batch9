package list;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> listInt = new LinkedList<>();

        listInt.add(4);
        listInt.add(6);
        listInt.add(5);
        listInt.addLast(55);
        listInt.addFirst(6);

        listInt.remove(3);

        for(int i: listInt){
            System.out.println(i);
        }

        System.out.println("Phần tử cuối cùng");
        System.out.println(listInt.getLast());
    }
}
