package item42;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

class Workout {

	private Integer duration;
	private String type;

	public Workout(Integer duration, String type) {
		this.duration = duration;
		this.type = type;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int compareByDuration(Workout a, Workout b) {
		return a.duration.compareTo(b.duration);
	}
}

// Suppose that the members of your workout application are
// contained in an array, and you want to sort the array by duration. You
// could use the following code
class WorkoutComparator implements Comparator<Workout> {

	@Override
	public int compare(Workout a, Workout b) {
		return a.getDuration().compareTo(b.getDuration());
	}
}

public class Main {

	public static void main(String[] args) {

		// 1. Reference to a static method
		List<String> names = Arrays.asList("1", "2", "3");

		// lambda equivalent
		names.forEach(name -> Integer.parseInt(name));
		System.out.println("lambda equivalent: " + names);

		// method reference
		names.forEach(Integer::parseInt);
		System.out.println("method reference: " + names);

		// 2. Reference to an instance method of a particular object
		Workout[] workoutArray = { new Workout(60, "chest&back"), new Workout(50, "biceps&triceps"),
				new Workout(70, "shoulders&legs") };
		WorkoutComparator myWorkoutComparator = new WorkoutComparator();

		// The method reference myWorkoutComparator::compare invokes the method
		// compare that is part of the object myWorkoutComparator.
		// The JRE infers the method type arguments, which in this case are
		// (Workout, Workout).
		Arrays.sort(workoutArray, myWorkoutComparator::compare);

		// lambda equivalent
		Arrays.sort(workoutArray, (a, b) -> myWorkoutComparator.compare(a, b));

		List<Workout> list = Arrays.asList(workoutArray);
		list.forEach(a -> System.out.print(a.toString()));

		// 3. Reference to an instance method of an arbitrary object of a
		// particular type

		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareTo); // method reference
		Arrays.sort(stringArray, (a, b) -> a.compareTo(b)); // The equivalent lambda expression for the method reference
		
		// 4. Reference to a Constructor
		
		
	}

}
