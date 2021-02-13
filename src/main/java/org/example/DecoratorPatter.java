package org.example;

import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

public class DecoratorPatter {

    public static class Calculator implements IntUnaryOperator {
        @Override
        public int applyAsInt(int value) {
            return 100 + value;
        }
    }

    public static int multiply(int value) {
        System.out.println("multiply");
        return value * 2;
    }

    public static int add(int value) {
        System.out.println("add");
        return value + 5;
    }

    public static int subtract(int value) {
        System.out.println("subtract");
        return value - 7;
    }

    public static int calculate(int value, IntUnaryOperator... values) {
        return Stream.of(values)
                .reduce(IntUnaryOperator.identity(), IntUnaryOperator::andThen).applyAsInt(value);
    }

    public static void main(String[] args) {
        System.out.println("-->" + new Calculator()
                .andThen(DecoratorPatter::multiply)
                .andThen(DecoratorPatter::add)
                .andThen(DecoratorPatter::subtract)
                .applyAsInt(1));

        System.out.println("-->" + calculate(1,
                new Calculator(),
                DecoratorPatter::multiply,
                DecoratorPatter::add,
                DecoratorPatter::subtract));
    }
}
