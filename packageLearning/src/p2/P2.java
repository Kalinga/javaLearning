package p2;

import p.P1;

public class P2 extends P1 {
	protected void test() {
		System.out.println(Thread.currentThread().getStackTrace()[1]);
	}

}
