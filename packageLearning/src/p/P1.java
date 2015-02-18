package p;

public class P1 {
	protected void test() {
		System.out.println(Thread.currentThread().getStackTrace()[1]);
	}

}
