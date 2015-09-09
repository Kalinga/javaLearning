package com.journaldev.java.dependencyinjection.injector;

import com.journaldev.java.dependencyinjection.consumer.Consumer;
import com.journaldev.java.dependencyinjection.consumer.MyDIFBApplication;
import com.journaldev.java.dependencyinjection.service.FBServiceImpl;

public class FBServiceInjector implements MessageServiceInjector {

	@Override
	public Consumer getConsumer() {
		System.out.println(this.getClass().getSimpleName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName() );
		Consumer app = new MyDIFBApplication(new FBServiceImpl());
		return app;
	}

}
