package item43;

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
	
	public Workout(String type){
		this.duration = Integer.valueOf(60);
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


class WorkoutComparator implements Comparator<Workout> {

	@Override
	public int compare(Workout a, Workout b) {
		return a.getDuration().compareTo(b.getDuration());
	}
}

public class Main {

	public static void main(String[] args) {

		// 1. Reference to a static method
		referenceToStaticMethod();

		// 2. Reference to an instance method of a particular object
		referenceToInstance();

		// 3. Reference to an instance method of an arbitrary object of a particular type
		referenceToArbitraryObject(); 
		
		// 4. Reference to a Constructor
		referenceToContructor();
	}

	/**
	 * Reference to a constructor	
	 * @author suleyman.yildirim
	 */
	private static void referenceToContructor() {
		List<String> workputTypes = Arrays.asList("chest", "back", "legs");	
		Workout[] workouts = workputTypes.stream()
			.map(Workout::new)
			.toArray(Workout[]::new);
		for (Workout workout : workouts) {
			System.out.println("Type: " + workout.getType() + " -Duration: "+ workout.getDuration());
		}
	}

	/**
	 * Reference to an instance method of an arbitrary object of a particular type
	 * @author suleyman.yildirim
	 */
	private static void referenceToArbitraryObject() {
		String[] family = { "Suleyman", "Canan", "Fatma", "Omur", "Tomis" };
		// method reference
		
		Arrays.sort(family, String::compareTo);
		// The equivalent lambda expression for the method reference
		Arrays.sort(family, (a, b) -> a.compareTo(b));
	}

	/**
	 * Reference to an instance method of a particular object
	 * @author suleyman.yildirim
	 */
	private static void referenceToInstance() {
		Workout[] workoutArray = { 
				new Workout(60, "chest&back"),
				new Workout(50, "biceps&triceps"),
				new Workout(70, "shoulders&legs") 
		};
		
		WorkoutComparator myWorkoutComparator = new WorkoutComparator();
		
		//method reference
		Arrays.sort(workoutArray, myWorkoutComparator::compare);
		
		//lambda equivalent
		Arrays.sort(workoutArray, (a, b) -> myWorkoutComparator.compare(a, b));
		
		List<Workout> list = Arrays.asList(workoutArray);
		list.forEach(a -> System.out.println(a.toString()));
	}

	/**
	 * Reference to a static method
	 * @author suleyman.yildirim
	 */
	private static void referenceToStaticMethod() {
		List<String> numbers = Arrays.asList("1", "2", "3");

		// lambda equivalent
		numbers.forEach(name -> Integer.parseInt(name));
		System.out.println("lambda equivalent: " + numbers);

		// method reference
		numbers.forEach(Integer::parseInt);
		System.out.println("method reference: " + numbers);
	}

}
