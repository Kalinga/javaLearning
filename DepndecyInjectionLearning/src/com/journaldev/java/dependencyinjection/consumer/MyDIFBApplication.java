package com.journaldev.java.dependencyinjection.consumer;

import com.journaldev.java.dependencyinjection.service.MessageService;

public class MyDIFBApplication implements Consumer{

	private MessageService service;
	
	public MyDIFBApplication(MessageService svc){
		System.out.println(this.getClass().getSimpleName());
		this.service=svc;
	}
	
	public MyDIFBApplication(){}

	@Override
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}

}
