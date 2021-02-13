package org.example;

import java.util.List;

public class CommandPattern {
    public static void main(String[] args) {
        List<Runnable> tasks = List.of(
                CommandPattern::task1,
                CommandPattern::task2,
                CommandPattern::task3
        );
        execute(tasks);
    }

    private static void execute(List<Runnable> tasks) {
        tasks.forEach(Runnable::run);
    }

    private static void task3() {
        System.out.println("task3");
    }

    private static void task2() {
        System.out.println("task2");
    }

    private static void task1() {
        System.out.println("task1");
    }
}
