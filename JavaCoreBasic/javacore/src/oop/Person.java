package oop;

import oop.English;

public class Person {
    private String name;
    private int age;
    private final int count= 0;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sleep(){
        System.out.println("Sleeping");
    }

    public void breath(String name){
        System.out.println(name+" is breathing");
    }

//    public double sum(double d1, double d2){
//        return d1+d2;
//    }

    private void privateMethod(){
        System.out.println("private method");
    }
}


class Student extends Person implements English, Math {
    private String classId;
    private double point;

    public Student() {
    }

    public Student(String name, int age, String classId, double point) {
        super(name, age);
        this.classId = classId;
        this.point = point;
    }

    @Override
    public void sleep(){
        System.out.println("Zzzz");
    }

    @Override
    public void speakEnglish() {
        System.out.println("I can speak English");
    }

    @Override
    public String learnMath(){
        return "I am learning Math";
    }
}

class Teacher extends Person{
    private String subject;

    public Teacher() {
    }

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void sleep(){
        System.out.println("Khò khò");
    }

    //over loading
    public String sum(String s1, String s2){
        return s1+s2;
    }

    public double sum(double d1, double d2){
        return d1+d2;
    }
}
