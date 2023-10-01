package com.example.webfluxpractice;

import java.io.IOException;

import reactor.core.publisher.Flux;

public class Exercise8 {

	public static void main(String[] args) throws IOException {

		// Use ReactiveSources.intNumbersFluxWithException()

		// Print values from intNumbersFluxWithException and print a message when error
		// happens
		 ReactiveSources.intNumbersFluxWithException().subscribe(System.out::println,error->System.out.println(error));

		// Print values from intNumbersFluxWithException and continue on errors
		ReactiveSources.intNumbersFluxWithException()
				.onErrorContinue((e, item) -> System.out.println("Error..." + e.getMessage()))
				.subscribe(System.out::println);

		// Print values from intNumbersFluxWithException and when errors
		// happen, replace with a fallback sequence of -1 and -2
		ReactiveSources.intNumbersFluxWithException()
				.onErrorResume(e->Flux.just(-1,-2))
				.subscribe(System.out::println);

		System.out.println("Press a key to end");
		System.in.read();
	}

}
