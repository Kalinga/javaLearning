package b;
import a.A;

public class B extends A{
	public static void main(String[] args) {
		//A aObj  = new A();
		//A aObj1 = new B();
		//B bObj  = new A(); //Can not convert from A to B
		B bObj1 = new B();
		//aObj.display(); // The display() method from type A is not visible
		//aObj1.display(); // The display() method from type A is not visible
		bObj1.display(); 
	}
	
}
