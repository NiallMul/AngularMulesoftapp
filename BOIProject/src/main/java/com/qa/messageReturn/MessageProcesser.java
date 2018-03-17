package com.qa.messageReturn;


import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;

public class MessageProcesser {
	public MuleMessage processMessage(MuleMessage message)
	{
		 

	
	String myMessage=message.getPayload().toString();

	String[] parts = myMessage.split("=");
	String part1 = parts[0];
	String part2 = parts[1];
	String[] parts2=part1.split("ParameterMap");
	String result= parts2[1];
	result=result.substring(2);

	message.setPayload(result);
	System.out.println("JAVA!!!!!!!!----------------------------->"+result);
	
		return message;
	}

}
