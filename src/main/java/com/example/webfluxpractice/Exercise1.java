package com.example.webfluxpractice;

public class Exercise1 {

	public static void main(String[] args) {

		// Use StreamSources.intNumbersStream() and StreamSources.userStream()

		// Print all numbers in the intNumbersStream stream
		StreamSources.intNumbersStream().forEach(System.out::println);

		// Print numbers from intNumbersStream that are less than 5
		StreamSources.intNumbersStream().filter(x -> x < 5).forEach(System.out::println);

		// Print the second and third numbers in intNumbersStream that's greater than 5
		StreamSources.intNumbersStream().filter(x -> x > 5).skip(1).limit(2).forEach(System.out::println);

		// Print the first number in intNumbersStream that's greater than 5.
		// If nothing is found, print -1
		Integer number = StreamSources.intNumbersStream().filter(x -> x > 5).findFirst().orElse(-1);
		System.out.println(number);

		// Print first names of all users in userStream
		StreamSources.userStream().map(x -> x.getFirstName()).forEach(System.out::println);

		// Print first names in userStream for users that have IDs from number stream
//        StreamSources.intNumbersStream().flatMap(id->StreamSources.userStream().filter(x->x.getId()==id))
//        .map(x->x.getFirstName())
//        .forEach(x->System.out.println(x));

		StreamSources.userStream()
		.filter(u -> StreamSources.intNumbersStream()
				.anyMatch(i -> i == u.getId()))
				.forEach(System.out::println);

	}

}
