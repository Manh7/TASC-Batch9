package java_core_basic.memory;

class Person {
    String name;
    Person(String name) { this.name = name; }
}

public class Core5 {
    static void rename(Person p, String newName) {
        // p là biến cục bộ nằm trên Stack, giữ BẢN SAO của tham chiếu
        p.name = newName;     // thay đổi trạng thái OBJECT trên Heap
        p = new Person("X");  // đổi tham chiếu cục bộ p (trên Stack) -> không ảnh hưởng caller
    }

    public static void main(String[] args) {
        int x = 10;                 // primitive trên Stack
        Person a = new Person("A"); // 'a' là tham chiếu trên Stack, object Person ở Heap

        rename(a, "B");
        System.out.println(x);      // 10 (primitive độc lập)
        System.out.println(a.name); // "B" (object trên Heap bị đổi tên)
    }
}
