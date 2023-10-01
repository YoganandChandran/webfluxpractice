package com.example.webfluxpractice;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(System.out::println);

        // Get the value from the Mono into an integer variable
        //One way
        ReactiveSources.intNumberMono().blockOptional().stream().mapToInt(x->x).forEach(System.out::println);
        
        //Another Way
        Integer number = ReactiveSources.intNumberMono().block();
        System.out.println(number);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
