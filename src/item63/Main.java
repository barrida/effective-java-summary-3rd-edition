package item63;

public class Main {

	public static void main(String[] args) {
		stringConcatenation();
		stringBuilderConcatenation();
	}

	// Inappropriate use of string concatenation - Performs poorly!
	public static String stringConcatenation() {
		long start = System.currentTimeMillis();
		String result = "";
		for (int i = 0; i < 100000; i++)
			result += "concat"; // String concatenation
		long end = System.currentTimeMillis();
		System.out.println("String Concatination Elapsed Time: " + (end - start) + " miliseconds");
		return result;
	}

	// Correct usage
	public static String stringBuilderConcatenation() {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder(100000);
		for (int i = 0; i < 100000; i++)
			sb.append("concat");
		long end = System.currentTimeMillis();
		System.out.println("String Builder Elapsed Time: " + (end - start) + " miliseconds");
		return sb.toString();
	}

}
