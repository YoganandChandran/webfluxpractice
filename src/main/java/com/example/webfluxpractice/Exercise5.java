package com.example.webfluxpractice;

import java.io.IOException;
import java.util.concurrent.Flow.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.usersFlux()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
        		number -> System.out.println(number),
        		err -> System.out.println(err.getLocalizedMessage()),
        		()-> System.out.println("completed")
        		);

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T>{
	public void hookSubscriber(Subscription subscription) {
		System.out.println("Subcriber Happens...");
		request(1);
	}
	
	public void hookOnNext(T value) {
		System.out.println(value.toString()+" Values received..");
		request(3);
	}
}