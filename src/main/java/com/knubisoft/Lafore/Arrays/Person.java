package com.knubisoft.Lafore.Arrays;

public class Person {
    private final String lastName;
    private final String firstName;
    private final int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public void displayPerson() {
        System.out.print("  Last name: " + lastName);
        System.out.print(",  First name: " + firstName);
        System.out.println(",   Age: " + age);
    }

    public String getLastName() {
        return lastName;
    }
}
