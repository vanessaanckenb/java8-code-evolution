package br.com.vanessaancken.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JavaCodeEvolution {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Vanessa Ancken");
        names.add("Camila Ancken");
        names.add("Regiane Costa");

        // Before java 8
        for (String name: names) {
            System.out.println("Before Java 8: " + name);
        }
        System.out.println();


        // From Java 8
        // Example A: Separeted class
        names.forEach(new PrintStringConsumer());
        System.out.println();


        // Example B: Anonymous Class referenced by a var
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("With anonymous class referenced by a var: " + s);
            }
        };
        names.forEach(consumer);
        System.out.println();


        // Example C: Anonymous Class directly in foreach
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("With anonymous class directly in foreach: " + s);
            }
        });
        System.out.println();


        //Example D: Lambda
        names.forEach((String s) -> {
            System.out.println("With lambda: " + s);
        });
        System.out.println();


        //Example E: Lambda
        names.forEach(s -> System.out.println("With lambda: " + s));
        System.out.println();


        //Example F: Lambda referennced by a var
        Consumer<String> consumerWithLambda = s1 -> System.out.println("Another example of lambda: " + s1);
        names.forEach(consumerWithLambda);
        System.out.println();


        //Example G: method referenced
        names.forEach(System.out::println);
        System.out.println();


        //Example G: method referenced in a var
        Consumer<String> consumerWithMethodReferenced = System.out::println;
        names.forEach(consumerWithMethodReferenced);
        System.out.println();
    }
}

class PrintStringConsumer implements Consumer<String>{
    @Override
    public void accept(String s) {
        System.out.println("With separeted class: " + s);
    }
}
