package a;

public class A {
	protected void display () {
		System.out.println("A::display");
	}
		
	public void show () {
		//System.out.println("A::show()");
		display();
	}

}
