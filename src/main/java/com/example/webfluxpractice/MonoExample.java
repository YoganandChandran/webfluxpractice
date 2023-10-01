package com.example.webfluxpractice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        // Creating a Mono that emits a single value
        Mono<String> mono = Mono.just("Hello, World!").log();

        // Subscribing to the Mono to perform an action when it emits a value or completes
        mono.subscribe(
            value -> System.out.println(value), // This block is executed when the Mono emits a value
            error -> System.err.println("Error: " + error), // This block is executed if there's an error
            () -> System.out.println("Mono completed") // This block is executed when the Mono completes
        );
        
        Flux<String> flux = Flux.just("Yoga","Kirithigan","Sundar","Suhail","These are team MBS")
        		.concatWithValues("Daniel","aBDUL")
        		.log();
        flux.subscribe(System.out::println);
    }
}
