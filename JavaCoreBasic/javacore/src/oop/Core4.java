package java_core_basic.oop;

public class Core4 {
    public static void main(String[] args){
        Student s1 = new Student();
        s1.speakEnglish();


        Teacher t1 = new Teacher("Hoa", 27 , "Math");

        System.out.println(t1.sum("Hello", " Java"));
        System.out.println(t1.sum(5.654, 9.76));
    }
}
