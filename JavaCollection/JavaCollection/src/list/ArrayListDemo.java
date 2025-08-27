package list;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main (String[] args){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Cam");
        list1.add("Ổi");
        list1.add("Đào");
        list1.add("Mận");
        list1.add("Hồng");

        System.out.println("Truy cập trực tiếp:");
        System.out.println(list1.get(3));

        list1.remove("Ổi");
        list1.set(3,"Chanh");

        System.out.println("\nDuyệt list:");
        for(String x : list1){
            System.out.println(x);
        }
    }
}
