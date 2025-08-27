package set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Integer> intSet= new HashSet<>();
        intSet.add(4);
        intSet.add(10);
        intSet.add(25);
        intSet.add(8);
        intSet.add(12);
        intSet.add(4); // không cho phép phần tử trùng lặp

        intSet.remove(10);

        System.out.println(intSet);
        System.out.println("set contained 12: "+ intSet.contains(12));
        System.out.println("set size: "+intSet.size());

//        // duyệt phần tử trong set
//
//        Iterator<Integer> iterator = intSet.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        // Duyệt qua các phần tử trong HashSet
        System.out.println("Các phần tử trong HashSet:");
        for (int s : intSet) {
            System.out.println(s);
        }

    }
}
