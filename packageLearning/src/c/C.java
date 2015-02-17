package c;

import a.A;

public class C extends A{
	//static void display() { // This static method can not hide instance method of A
	// private void display() { // Can not reduce the visibility of the inherited method from A
	// public void display() { //OK
	protected void display() {	
		System.out.println("C::display");
	}
	
	protected void displayC() {	
		System.out.println("C::displayC");
	}	
	
//	public void show () {
//		System.out.println("C::show()");
//	}
	
	public static void main(String[] args) {
		//A aObj  = new A();
		A aObj1 = new C();
		//C cObj  = new A(); //Can not convert from A to B
		C cObj1 = new C();
		//aObj.display(); // The display() method from type A is not visible
		//aObj1.display(); // The display() method from type A is not visible
		cObj1.display();
		cObj1.displayC();
		aObj1.show();
		//http://www.javatpoint.com/access-modifiers
	}

}
