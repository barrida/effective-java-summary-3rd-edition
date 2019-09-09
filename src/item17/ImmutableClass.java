package item17;

/**
 * 1 .Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
 * 2. Make all fields final and private.
 * 3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more sophisticated approach is to make the constructor private and construct instances in factory methods.
 * 4. If the instance fields include references to mutable objects, don't allow those objects to be changed:
 *		Don't provide methods that modify the mutable objects.
 **		Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor; if necessary, create copies, and store references to the copies. 
 **     Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
 
 @author suleyman.yildirim
 *
 */
//Ensure that the class can’t be extended.
public final class ImmutableClass {

	// Make all fields final and privat
	private final int var1;

	private final int var2;

	// Immutable object can be in exactly one state, the state in which it was
	// created.
	// Immutable objects are inherently thread-safe; they require no
	// synchronization. Therefore,they can be shared freely.
	// You don't need to make defensive copies of them as they would be
	// equivalent to the originals
	
	public ImmutableClass(int var1, int var2){
		this.var1 = var1;
		this.var2 = var2;
	}
	
	public int getVar1() {
		return var1;
	}

	public int getVar2() {
		return var2;
	}

}
