package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObserverPatter {

    public static class Observable {
        private final List<Consumer<String>> listeners = new ArrayList<>();

        public void add(Consumer<String> listener) {
            listeners.add(listener);
        }

        public void remove(Consumer<String> listener) {
            listeners.remove(listener);
        }

        public void fire(String message) {
            listeners.forEach(listener -> listener.accept(message));
        }
    }

    public static void main(String[] args) {
        Observable observable = new Observable();
        observable.add(s -> System.out.println("s1 = " + s));
        observable.add(s -> System.out.println("s2 = " + s));
        observable.add(s -> System.out.println("s3 = " + s));

        observable.fire("Hello!");
    }
}
