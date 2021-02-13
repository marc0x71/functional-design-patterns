package org.example;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Person {
    String firstName;
    String lastName;
    int age;
}
