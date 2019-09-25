# The Summary of Effective Java (3rd edition)
Notes on the Effective Java 3rd Edition by Bloch, Joshua and code samples.

### Table of Contents  
#### [Classes and Interfaces](#4)
  
[Item 15: Minimize the accessibility of classes and members](#15)  
[Item 16: In public classes, use accessor methods, not public fields](#16)       
[Item 17: Minimize Mutability](#17)  
[Item 18: Favor composition over inheritance](#18)  
[Item 22: Use interfaces only to define types](#22)  
[Item 25: Limit source files to a single top-level class](#25)  

#### [General Programming](#9)

[Item 57: Minimize the scope of local variables](#57)  
[Item 58: Prefer for-each loops to traditional for loops](#58)  
[Item 59: Know and use the libraries](#59)  
[Item 60: Avoid float and double if exact answers are required](#60)  
[Item 61: Prefer primitive types to boxed primitives](#61)  
[Item 62: Avoid strings where other types are more appropriate](#62)  
[Item 63: Beware the performance of string concatenation](#63)  
[Item 64: Refer to objects by their interfaces](#64)  
[Item 67: Optimize judiciously](#67)  

<a name="4"/>

# Classes and Interfaces

<a name="15">

## Item 15: Minimize the accessibility of classes and members  
  
This section is a review of encapsulation (information hiding)  

Advantages of encapsulation (information hiding):  
- Speeds up the system development as components can be developed in parallel
- Enables effective performance tuning. Components can be optimized seperately without affecting the others 
- Increases software reuse as components aren’t tightly coupled
- Decrease the risk in building large systems, improves resilience 

Proper use of these modifiers is essential to information hiding. Make each class or member as inaccessible as possible.  

**Classes**  

- For top-level (non-nested) classes and interfaces, there are only two possible access levels: package-private and public.
- If a package-private top-level class or interface is used by only one class, consider making the top-level class a private static nested class of the sole class that uses it

**Members (fields, methods, nested classes, and nested interfaces)**   

Modifier | Class | Package | Subclass | World
--- | --- | --- | --- | ---
public | Y | Y | Y | Y
protected | Y | Y | Y | N
package-private (no modifier) | Y | Y | N | N
private | Y | N | N | N

**Ensure that objects referenced by public static final fields are immutable**

```
// Problem: Potential security hole! Clients will be able to modify the contents of the array
public static final Thing[] VALUES = { ... };
```

```
// Solution 1: Make the array private and add a public immutable list 
private static final Integer[] PRIVATE_VALUES = { ... };
public static final List <Integer> VALUES = Collections.unmodifiableList( Arrays.asList( PRIVATE_VALUES));
```
```
// Solution 2: Make the array private and create a public static final function that returns a copy an array 
private static final Integer[] PRIVATE_VALUES = { ... };
public static final Integer[] getValues() {
  return PRIVATE_VALUES.clone();
}
```

<a name="16">

## Item 16: In public classes, use accessor methods, not public fields  

If a class is accessible outside its package, provide accessor methods. The classic approach is as follows:  
- Private fields
- Public accessor methods (getters) and mutators (setters)	

However, "package-private" and "private nested classes" can expose their data fields.


<a name="17">  

## Item 17: Minimize Mutability  

- Immutable object can be in exactly one state, the state in which it was created  
- Immutable objects are inherently thread-safe; they require no synchronization. Therefore, they can be shared freely  
- You don't need to make defensive copies of them as they would be equivalent to the originals  
- Classes should be immutable unless there’s a very good reason to make them mutable  
- Declare every field private final unless there’s a good reason to do otherwise  

How to make a class immutable? This is a classic interview question  
1. Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
2. Make all fields final and private.
3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more sophisticated approach is to make the constructor private and construct instances in factory methods.
4. If the instance fields include references to mutable objects, don't allow those objects to be changed:
 - Don't provide methods that modify the mutable objects.
 - Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.

<a name="18">

## Item 18: Favor composition over inheritance (when one class extends another)  

**Inheritance violates encapsulation unless the superclass’s authors have designed it specifically for the purpose of being extended**

**How to use composition and forwarding technique**  

Let's say you want to extend the functionality of the Set<E> interface.  

1. Create a Forwarding class

- Step 1. Composition  
Give your class a private field that references an instance of the existing class (Set<E>)

```
//your forwarding class
public class ForwardingSet<E> implements Set<E> {
 	private final Set<E> s;
 	...
}

```
- Step 2. Forwarding  
Each instance method in the new class invokes the corresponding method on the contained instance of the existing class and returns the results  

```
    ...
	@Override
	public boolean add(E e) {
		return s.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return s.addAll(c);
	}
	...
```

2. Extend the forwarding class  

```
public class InstrumentedSet<E> extends ForwardingSet<E> {

	// The number of attempted element insertions
	private int addCount = 0;
    
        // constructor 
	public InstrumentedSet(Set<E> s) {
		super(s);
	}

	@Override
	public boolean add(E e) {
	...your new implementation ...
	}
	
	....other overriden methods...
	
	//your new method
	public int getAddCount() {
		return addCount;
	}
```

3. And it is ready to use  

```
	InstrumentedSet<String> s2 = new InstrumentedSet<>(new TreeSet<>());
	s2.addAll(Arrays.asList("Snap", "Crackle", "Snap"));
	System.out.println("Total: "  + s2.getAddCount());

```

<a name="22">

## Item 22: Use interfaces only to define types  

Interfaces should be used only to define types. They should not be used merely to export constants.

**The constant interface pattern is a poor use of interfaces**  

```
// Constant interface antipattern - do not use! 
public interface PhysicalConstants { 

static final double AVOGADROS_NUMBER = 6.022_140_857e23; 
static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23; 
static final double ELECTRON_MASS = 9.109_383_56e-31;

}

```

**If the constants are best viewed as members of an enumerated type, you should export them with an enum type (Item 34)**  
**If the constants are NOT strongly tied to an existing class or interface, export the constants with a noninstantiable utility class**

```
public class ConstantsFoo {

	private ConstantsFoo(){} // prevent instantiation
	
	public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
	public static final double BOLTZMANN_CONST = 1.380_648_52e-23;
	public static final double ELECTRON_MASS = 9.109_383_56e-31;
}


```

<a name="25">  

# Item 25: Limit source files to a single top-level class  

Never put multiple top-level classes or interfaces in a single source file. Following this rule guarantees that you can’t have multiple definitions for a single class at compile time.  


```
// Static member classes instead of multiple top-level classes

public class Test { 

  public static void main( String[] args) { 
  	System.out.println( Utensil.NAME + Dessert.NAME); 
  } 
  
  private static class Utensil { 
  	static final String NAME = "pan";
  }
    
  private static class Dessert { 
  	static final String NAME = "cake"; 
  } 
}


```

Bloch, Joshua. Effective Java (pp. 212-213). Pearson Education. Kindle Edition.  


<a name="9"/>

# General Programming

<a name="57"/> 

## Item 57: Minimize the scope of local variables
**Declare the variable where it is first used**   
The reader will not be distracted to figure out where the variable is declared  

**Prefer for loops to while loops, assuming the contents of the loop variable aren’t needed after the loop terminates.**  

Advantages of for lops over while loops    
- Eliminate copy-paste errors
- Shorter and more readable
- Declare loop variables, limiting their scope to the exact region where they’re needed.

```
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		// Not a best practice! Variable "i" might be used somewhere later, such
		// as the for-loop below
		Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			Integer value = i.next();
			System.out.println(value);
		}

		// Idiom for iterating when you need the iterator but the the best. Look
		// at the Item 58 for better approach!
		for (Iterator<Integer> j = list.iterator(); j.hasNext();) {
			Integer value = j.next();
			System.out.println(value);
		}

		// ...Then you wrote a 100 lines of code here and forgot the Iterator
		// variable "i"...

		// What happens if you accidentally use the variable "i" below? :)
		for (Iterator<Integer> j = list.iterator(); j.hasNext();) {
			Integer value = i.next(); // ???
			System.out.println(value);
		}


```

<a name="58"/> 

## Item 58: Prefer for-each loops to traditional for loops

Traditional for loop distracts the programmer because of index variables. It gives you many chances to use the wrong variable. for-each loop hides the iterator or index variable. 

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
	
		List<String> listString = new ArrayList<String>();
		listString.add("string1");
		listString.add("string2");
		listString.add("string3");
		
		// Iterate with for-each loop. Better then the traditional one!
		for (Integer integer : list) {
			System.out.println(integer);
		}
		
		// for-each loops are even greater when it comes to nested iteration
		for (Integer integer : list) {
			for (String string : listString) {
				System.out.println("nested foreach loop");
			}
		}
		
There are some cases that you **do need** to use an ordinary for loop. For instance, you want to replace the value of a list while you iterate a collection. Please check the book for other examples.

		// Transforming a list with ordinary for loop
		for (int i = 0 ; i < integerList.size(); i++) {
			System.out.println("before: " + integerList.get(i));
			integerList.set(i, 3);
			System.out.println("after: "+integerList.get(i));
		}


<a name="59"/> 

## Item 59: Know and use the libraries
The takeaway from this section is "Don't reinvent the wheel!". The author explains the pitfalls of most developers fall into by giving random number generation example. Check the library before you try an ad hoc solution.

1. By using a standard library, you take advantage of the knowledge of the experts who wrote it and the experience of those who used it before you.
2. The performance of standard libraries tends to improve over time, with no effort on your part.
3. You don’t have to waste your time writing ad hoc solutions to problems that are only marginally related to your work.

<a name="60"/>  

## Item 60: Avoid float and double if exact answers are required  

**Use BigDecimal, int, or long for monetary calculations instead of float and double types**  

Advantages of using BigDecimal  
- You want to have full control over rounding
- You don’t mind keeping track of the decimal point
- You don’t mind the inconvenience and cost of not using a primitive type

Disadvantages to using BigDecimal 
- Less convenient than using a primitive arithmetic type
- A lot slower 
	
**An alternative to using BigDecimal is to use "int" or "long", if you wan't to keep track of the decimal point yourself**  

- If the quantities don’t exceed nine decimal digits, use int
- If the quantities exceed eighteen digits, use long
- If the quantities might exceed eighteen digits, use BigDecimal

<a name="61"/>  

## Item 61: Prefer primitive types to boxed primitives 

**Applying the == operator to boxed primitives is almost always wrong. The two boxed primitives can have same value and different identities, whereas the primitives have only values. Check the code below:** 
```
int a = new Integer(10);
int b = new Integer(10);
 	
// The values of the boxed primitives are the same, so true.
System.out.println(a == b); // true

// The identities the boxed primitives are different, so false
System.out.println(new Integer(10) == new Integer(10)); // false
	
```
 **When you mix primitives and boxed primitives in an operation, the boxed primitive is auto-unboxed. If a null object reference is auto-unboxed, you get a NullPointerException**   

```
static Integer i;
public static void main(String[] args) {
    if (i == 42) {
	System.out.println("Do not mix primitive and boxed");
   }
}	
``` 
**Auto-boxing and auto-unboxing cause significant performance degradation. Compare the performance of the code snippets below:** 

```
Integer sumBoxed = 0L;
for (int i = 0; i < Integer.MAX_VALUE; i++) { 
    sumBoxed += i;
}	

...
int sumBoxed = 0L;
for (int i = 0; i < Integer.MAX_VALUE; i++) {
    sumBoxed += i;
}	
```

<a name="62"/>

## Item 62: Avoid strings where other types are more appropriate

When a piece of data comes into a program from a file, from the network, or from keyboard input, it is often in string form.

1. If the data is numeric, translate it into the **int, float, or BigInteger**.  
2. If it’s the answer to a yes-or-no question, translate it into an appropriate **enum type or a boolean**.  
3. If there’s an appropriate value type, whether primitive or object reference, you should use it. Otherwise, you should write one.

<a name="63"/>

## Item 63: Beware the performance of string concatenation 

The string concatenation operator (+) does not scale. Using the string concatenation operator repeatedly to concatenate n strings requires time quadratic in n.   

Don’t use the string concatenation operator to combine more than a few strings unless performance is irrelevant. Use StringBuilder’s append method in place of a String.

```
// Inappropriate use of string concatenation - Performs poorly!
public static String stringConcatenation() {
     	String result = ""; 
	for (int i = 0; i < 100000; i++) 
		result += "concat"; // String concatenation
	return result;
}
```
```
// Correct use of string concatenation
public static String stringBuilderConcatenation() { 
	StringBuilder result = new StringBuilder(100000);
	for (int i = 0; i < 100000; i++) 
		result.append("concat");
	return result.toString();
}
```
<a name="64"/>
      
## Item 64: Refer to objects by their interfaces  

**Use interfaces over classes to refer to objects**  

If you get into the habit of using interfaces as types, your program will be much more flexible. If you decide that you want to switch implementations, all you have to do is change the class name in the constructor. Check the the general-purpose implementations of the ```List``` interface below.

```
// Use interface as a type
List<String> arrayList = new ArrayList<String>()
List<String> linkedList = new LinkedList<String>() 
```

**Refer to an object by a class if no appropriate interface exists**

Value Classes | Class-based framework | Classes that implement an interface but also provide extra methods not found in the interfac
--- | --- | ---
*String* and *BigInteger* | Many *java.io* classes such as *OutputStream* | *PriorityQueue* has a comparator method that is not present on the *Queue* interface.

<a name="67"/>

## Item 67: Optimize judiciously

**Strive to write good programs - not fast ones. Speed will follow**   
But do think about performance while you are designing systems, especially while you are designing APIs, wire-level protocols, and persistent data formats. When you've finished building the system, measure its performance. If it's fast enough, you are done. If not, locate the source of the problem with the aid of a profiler and go to work optimizing the relevant parts of the system. The first step is to examine your choice of algorithms: no amount of low-level optimization can make up for a poor choice of algorithm. Repeat this process as necessary, measuring the performance after every change, until you are satisfied. 

**Strive to avoid design decisions that limit performance**   
Changing a fundamental facet of your design after the fact can result in an ill-structured system that is difficult to maintain and evolve. Therefore, you must think about performance during the design process.

**Consider the performance consequences of your API design decisions. In general, a good API design is consistent with good performance**   
Some of the examples: Making a public type mutable may require a lot of needless defensive copying (Item 50). Using composition over inheritance (Item 18), using an interface rather than implementation type (Item 64).

**97% of the time: premature optimization is the root of all evil**  
Often, optimizations have no measurable effect on performance. The main reason is that it's difficult to guess where your program is spending its time. The part of the program that you think is slow may not be at fault, in which case you'd be wasting your time trying to optimize it. 
