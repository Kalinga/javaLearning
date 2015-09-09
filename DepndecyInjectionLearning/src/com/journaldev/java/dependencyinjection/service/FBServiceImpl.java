package com.journaldev.java.dependencyinjection.service;

public class FBServiceImpl implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send SMS
		System.out.println("FB Post to "+rec+ " with Message="+msg);
	}

}
