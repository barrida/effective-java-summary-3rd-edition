package item61;

import java.time.LocalDateTime;

public class Main {

	static Integer i;
	public static void main(String[] args) {
		
		int a = new Integer(10);
		int b = new Integer(10);
		System.out.println(a == b); // true
		System.out.println(new Integer(10) == new Integer(10)); // false

		long startTime = System.currentTimeMillis();
		Integer sum = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}	
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time for boxed primitive: " + timeElapsed);
		
		long startTimePrimitive = System.currentTimeMillis();
		
		int sumPrimitive = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sumPrimitive += i;
		}
		long endTimePrimitive = System.currentTimeMillis();
		System.out.println("Execution time for primitive: " + (endTimePrimitive - startTimePrimitive));

		// NullPointerException
		if (i == 42) {
			System.out.println("NullPointerException");
		} 

	}

}
