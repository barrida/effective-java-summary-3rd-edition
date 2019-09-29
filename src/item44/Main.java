package item44;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

	// user defined functional interface
	@FunctionalInterface
	interface BodyMassIndex {
		double calculate(double weight, double height);
	}

	public static void main(String[] args) {
		unaryOperator();
		binaryOperator();
		predicates();
		function();
		functionalInterface();
		supplier();
		consumer();
	}

	private static void function() {

		Function<Integer, Integer> multiply = (value) -> value * 2;
		Function<Integer, Integer> add = (value) -> value + 3;
		
		//Returns a composed function that first applies the before function to its input, and then applies this function to the result.
		Function<Integer, Integer> addThenMultiply = multiply.compose(add);

		//Applies this function to the given argument.
		Integer result1 = addThenMultiply.apply(3);
		System.out.println("Function: (3 + 3) * 2 " + result1);
		
		//Returns a composed function that first applies this function to its input, and then applies the after function to the result.
		Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add);
		
		//Applies this function to the given argument.
		Integer result2 = multiplyThenAdd.apply(3);
		System.out.println("Function: 3 * 2 + 3 " + result2);

	}

	private static void consumer() {
		Consumer<String> consumer = Main::printFoods;
		consumer.accept("beef");
		consumer.accept("brown rice");
		consumer.accept("salad");
	}

	private static void printFoods(String food) {
		System.out.println(food);
	}

	private static void printSupplements(Supplier<String> supplier) {
		System.out.println(supplier.get());
	}

	// it takes no arguments but it returns some value by calling its get()
	// method.
	private static void supplier() {

		List<String> supplements = Arrays.asList("bcaa", "creatin", "fish oil", "vitamin C");
		supplements.stream().forEach(supplement -> {
			printSupplements(() -> supplement);
		});

		// This function returns a random value.
		Supplier<Double> randomValue = () -> Math.random();

		// Print the random value using get()
		System.out.println(randomValue.get());

	}

	private static void functionalInterface() {
		// lambda expression to define the calculate method
		BodyMassIndex bmi = (double weight, double height) -> weight / (height * height);
		double result = bmi.calculate(70, 1.7);
		System.out.print("\nThe Body Mass Index (BMI) is " + result + " kg/m2");
	}

	/**
	 * Reference: https://www.baeldung.com/java-predicate-chain
	 */
	private static void predicates() {

		// simple filter
		List<Integer> numbers = Arrays.asList(10, 3, 6, 8, 11);
		List<Integer> filteredNumbers = numbers.stream().filter(number -> number.intValue() > 5)
				.collect(Collectors.toList());
		System.out.println("Predicate - single filter: " + filteredNumbers);

		// complex filter
		List<Integer> multipleFilters = numbers.stream().filter(number -> number.intValue() < 10)
				.filter(number -> number.intValue() % 2 == 0).collect(Collectors.toList());
		System.out.println("Predicate - multiple filters: " + multipleFilters);

		// Combining predicates
		Predicate<Integer> predicate1 = number -> number.intValue() < 10;
		Predicate<Integer> predicate2 = number -> number.intValue() % 2 == 0;

		List<Integer> and = numbers.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
		System.out.println("Predicate - Predicate.and(): " + and);

		List<Integer> or = numbers.stream().filter(predicate1.or(predicate2)).collect(Collectors.toList());
		System.out.println("Predicate - Predicate.or(): " + or);

		List<Integer> negate = numbers.stream().filter(predicate1.negate()).collect(Collectors.toList());
		System.out.println("Predicate - Predicate.negate(): " + negate);

	}

	private static void binaryOperator() {
		BinaryOperator<Integer> operator = (x, y) -> x + y;
		System.out.println("Binary Operator: " + operator.apply(5, 10));

		BinaryOperator<Integer> bi = BinaryOperator.minBy(Comparator.reverseOrder());
		System.out.println("Binary Operator minBy: " + bi.apply(2, 3));

		BinaryOperator<Integer> bOpertorMax = BinaryOperator.maxBy((Integer t, Integer u) -> t.compareTo(u));
		System.out.println("Binary Operator maxBy: " + bOpertorMax.apply(10, 20));
	}

	private static void unaryOperator() {
		List<String> names = Arrays.asList("fistik", "kus", "tomis");
		names.replaceAll(String::toLowerCase);
		System.out.println(names);
	}

}
