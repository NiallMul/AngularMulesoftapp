package Trasformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class transform extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		MessageTransformer thisMessage = new MessageTransformer();
		return thisMessage.processMessage(message);
	}

}
