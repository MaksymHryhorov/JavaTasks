package com.knubisoft.base.reflection.model;

public class Cat {
    private String name;
    private int age;

    public Cat() {

    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayMeow() {

        System.out.println("Meow!");
    }

    public void jump() {

        System.out.println("Jump!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
