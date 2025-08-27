package set;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        // Khởi tạo TreeSet
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Thêm phần tử vào TreeSet
        treeSet.add(10);
        treeSet.add(5);
        treeSet.add(20);
        treeSet.add(15);

        System.out.println("TreeSet: " + treeSet); // [5, 10, 15, 20]

        // Truy cập phần tử đầu tiên và cuối cùng
        System.out.println("Phần tử nhỏ nhất: " + treeSet.first()); // 5
        System.out.println("Phần tử lớn nhất: " + treeSet.last());  // 20

        // Sử dụng các phương thức điều hướng
        System.out.println("Phần tử lớn hơn 10: " + treeSet.higher(10)); // 15
        System.out.println("Phần tử nhỏ hơn 15: " + treeSet.lower(15));  // 10

        // Xóa phần tử
        treeSet.remove(10);
        System.out.println("TreeSet sau khi xóa 10: " + treeSet); // [5, 15, 20]
    }
}
