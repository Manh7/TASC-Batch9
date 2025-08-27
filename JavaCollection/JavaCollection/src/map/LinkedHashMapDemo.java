package map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // Tạo LinkedHashMap với thứ tự thêm vào
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Thêm các phần tử vào LinkedHashMap
        linkedHashMap.put("Một", 1);
        linkedHashMap.put("Hai", 2);
        linkedHashMap.put("Ba", 3);
        linkedHashMap.put("Bốn", 4);

        // Truy cập phần tử để xem thứ tự
        linkedHashMap.get("Hai");
        linkedHashMap.get("Ba");

        // Duyệt qua các phần tử của LinkedHashMap sau khi truy cập
        System.out.println("LinkedHashMap sau khi truy cập " + linkedHashMap);
    }
}
