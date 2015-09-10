package com.journaldev.java.dependencyinjection.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.journaldev.java.dependencyinjection.consumer.Consumer;
import com.journaldev.java.dependencyinjection.consumer.MyDIApplication;
import com.journaldev.java.dependencyinjection.consumer.MyDIFBApplication;
import com.journaldev.java.dependencyinjection.injector.MessageServiceInjector;
import com.journaldev.java.dependencyinjection.service.MessageService;

public class MyDIApplicationJUnitTest {

	private MessageServiceInjector injector;
	@Before
	public void setUp(){
		//mock the injector with anonymous class
		injector = new MessageServiceInjector() {
			
			@Override
			public Consumer getConsumer() {
				//mock the message service
				return new Consumer (){
					@Override
					public void processMessages(String msg, String rec) {
						
						System.out.println( msg + ":" + rec);
					}
					
					@Override
					public void  setService(new MessageService() {
						@Override
						public void sendMessage(String msg, String rec) {
						
							System.out.println("Mock Message Service implementation");
						}
					}) {
						System.out.println("set the service to this annonymous class ");
					}
				};
			}
		};
	}
	
	@Test
	public void test() {
		Consumer consumer = injector.getConsumer();
		consumer.processMessages("Hi Pankaj", "pankaj@abc.com");
	}
	
	@After
	public void tear(){
		injector = null;
	}

}
