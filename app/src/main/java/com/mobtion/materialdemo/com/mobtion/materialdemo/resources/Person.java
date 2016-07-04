package com.mobtion.materialdemo.com.mobtion.materialdemo.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agvenegas on 7/1/16.
 */
public class Person {

    String name;
    String age;

    Person (String name, String age) {
        this.name = name;
        this.age = age;
    }

    public static List<Person> persons;

    public static void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
        persons.add(new Person("Pepe", "23 years old"));
        persons.add(new Person("Juan", "25 years old"));
        persons.add(new Person("Maria", "35 years old"));
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
        persons.add(new Person("Pepe", "23 years old"));
        persons.add(new Person("Juan", "25 years old"));
        persons.add(new Person("Maria", "35 years old"));
    }

    public String getName(){

        return name;
    }

    public String getAge(){

        return age;
    }
}
