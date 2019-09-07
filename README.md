# The Summary of the Effective Java (3rd edition)
Effective Java Summary, 3rd Edition

### Table of Contents  
#### [General Programming](#9)

[Item 61: Prefer primitive types to boxed primitives](#61)  
[Item 62: Avoid strings where other types are more appropriate](#62)  
[Item 63: Beware the performance of string concatenation](#63)  
[Item 64: Refer to objects by their interfaces](#64)  
[Item 67: Optimize judiciously](#67)  

<a name="9"/>
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
    	for (int i = 0; i < Integer.MAX_VALUE; i++) 
    	{ 
      	    sumBoxed += i;
    	}	
        ...
     	int sumBoxed = 0L;
    	for (int i = 0; i < Integer.MAX_VALUE; i++) 
    	{ 
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
