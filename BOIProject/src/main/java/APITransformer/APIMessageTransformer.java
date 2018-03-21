package APITransformer;

import org.mule.api.MuleMessage;

public class APIMessageTransformer {
	public MuleMessage processMessage (MuleMessage message){
		String myMessage=message.getPayload().toString();

		String[] parts = myMessage.split("64,");
		String result = parts[1];

		message.setPayload(result);
		return message;
	}
}
