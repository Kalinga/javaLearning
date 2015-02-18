package p;

import p2.P2;

public class P {

	public static void main(String[] args) {
		new P1().test();
		//new P2().test(); //Not visible
		P1 p1Obj = new P2();
		p1Obj.test(); // Protected Method overriding
	}

}
