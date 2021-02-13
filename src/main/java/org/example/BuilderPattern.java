package org.example;

import java.util.function.Consumer;

public class BuilderPattern {

    public String firstName;
    public String lastName;
    public int age;

    public Person build() {
        return new Person(firstName, lastName, age);
    }

    public BuilderPattern with(Consumer<BuilderPattern> func) {
        func.accept(this);
        return this;
    }

    public static void main(String[] args) {
        Person person = new BuilderPattern()
                .with(p -> p.firstName="Mario")
                .with(p -> p.lastName="Rossi")
                .with(p -> p.age=45)
                .build();

        System.out.println(person);
    }
}
