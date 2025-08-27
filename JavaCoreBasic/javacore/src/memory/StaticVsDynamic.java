package java_core_basic.memory;

public class StaticVsDynamic {
    // cấp phát tĩnh cấp lớp (method area/metaspace): tồn tại xuyên suốt thời gian class được nạp
    private static int GLOBAL_COUNT = 0;

    // instance field: mỗi object trên Heap có bản sao riêng
    private int id;

    public StaticVsDynamic() {
        GLOBAL_COUNT++;  // dùng chung
        this.id = GLOBAL_COUNT; // gán vào trường instance
    }

    public static void main(String[] args) {
        StaticVsDynamic o1 = new StaticVsDynamic(); // object #1 trên Heap
        StaticVsDynamic o2 = new StaticVsDynamic(); // object #2 trên Heap
        System.out.println("GLOBAL_COUNT = " + GLOBAL_COUNT); // 102
        System.out.println("o1.id = " + o1.id);               // 101
        System.out.println("o2.id = " + o2.id);               // 102
    }
}

