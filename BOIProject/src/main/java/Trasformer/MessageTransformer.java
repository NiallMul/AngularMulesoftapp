package Trasformer;

import org.mule.api.MuleMessage;

public class MessageTransformer {
	public MuleMessage processMessage (MuleMessage message){
		String myMessage=message.getPayload().toString();

		String[] parts = myMessage.split("64,");
		String result = parts[1];
		//String[] parts2=part1.split("=");
		//String result= parts2[0];

		message.setPayload(result);
		return message;
	}

}
