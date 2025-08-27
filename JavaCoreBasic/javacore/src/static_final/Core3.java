package java_core_basic.static_final;

public class Core3 {
    public static void main(String[] args){
        System.out.println(School.quantity);
        School.name();

        Human man = new Human();
//        man.age = 488; không gán đc

        System.out.println(man.age);
    }
}

class School{
    static int quantity =1000;
    static void name(){
        System.out.println("EPU");
    }
    static class Class{

    }
}

final class Human{
    final int age = 43;
    static final int MAX_VALUE= 6;
    final void sing(){
        System.out.println("La La La");
    }
    // không bị override
}


