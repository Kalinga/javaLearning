package com.journaldev.java.dependencyinjection.consumer;

import com.journaldev.java.dependencyinjection.service.MessageService;

public interface Consumer {

	void processMessages(String msg, String rec);
	 void setService(MessageService service);
}
