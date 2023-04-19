package ru.igojig.task_1;

public class Main {
    public static void main(String[] args) {
        Person person=new Person.PersonBuilder().setAge(45).setFirstName("Igor").setLastName("Zhigachev").build();
        System.out.println(person);

        Person person1=new Person.PersonBuilder().build();
        System.out.println(person1);
    }
}
