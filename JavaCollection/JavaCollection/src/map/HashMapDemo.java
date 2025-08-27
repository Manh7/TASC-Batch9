package map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"Ổi");
        map.put(3,"Hồng");
        map.put(5,"Ổi");
        map.put(null,"Đào");
        map.put(6,"Mít");
        map.put(9,"Cam");

        System.out.println("các phần tử trong hashmap");

        for(Integer i: map.keySet()){
            System.out.println(map.get(i));
        }

        // Lấy tất cả các cặp khóa-giá trị (sử dụng phương thức entrySet)
        System.out.println("List student and score:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
