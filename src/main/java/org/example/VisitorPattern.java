package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/*
 * Inspired by https://github.com/mariofusco/from-gof-to-lambda/blob/master/src/main/java/org/mfusco/fromgoftolambda/examples/visitor/VisitorLambda.java
 */
public class VisitorPattern {

    public static class Visitor<T> {
        private final Map<Class<?>, Function<Object, T>> functions = new HashMap<>();

        public <A> Acceptor<T, A> on(Class<A> clazz) {
            return new Acceptor<>(this, clazz);
        }

        public T visit(Object o) {
            return functions.get(o.getClass()).apply(o);
        }

        static class Acceptor<T, A> {
            private final Visitor<T> visitor;
            private final Class<A> clazz;

            Acceptor(Visitor<T> visitor, Class<A> clazz) {
                this.visitor = visitor;
                this.clazz = clazz;
            }

            @SuppressWarnings("unchecked")
            public void accept(Function<A, T> f) {
                visitor.functions.put(clazz, (Function<Object, T>) f);
            }
        }
    }

    public static class Square {
        private final double side;

        public Square(double side) {
            this.side = side;
        }
    }

    public static class Circle {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }
    }

    public static class Rectangle {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        Visitor<Double> areaVisitor = new Visitor<>();
        areaVisitor.on(Square.class).accept(s -> s.side * s.side);
        areaVisitor.on(Circle.class).accept(c -> Math.PI * c.radius * c.radius);
        areaVisitor.on(Rectangle.class).accept(r -> r.height * r.width);

        System.out.println(areaVisitor.visit(new Square(2)));
        System.out.println(areaVisitor.visit(new Circle(2)));
        System.out.println(areaVisitor.visit(new Rectangle(5, 3)));

        List<Object> figures = Arrays.asList(new Circle(2), new Square(2), new Rectangle(5, 3));
        double totalArea = figures.stream().map(areaVisitor::visit).reduce(0.0, Double::sum);
        System.out.println("totalArea = " + totalArea);
    }
}
