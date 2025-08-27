package map;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> tree1= new TreeMap<>();

        tree1.put(4,"Mạnh");
        tree1.put(1, "Hoàng");
        tree1.put(5,"Thanh");
        tree1.put(6,"Tuấn");
        tree1.put(3,"Hùng");
        tree1.put(8,"Hưng");

        System.out.println(tree1);

        System.out.println("first key: " +tree1.firstKey());
        System.out.println("Last value: " + tree1.get(tree1.lastKey()));
    }
}
