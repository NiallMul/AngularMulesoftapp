package com.qa.messageReturn;


import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;

import org.mule.transformer.AbstractMessageTransformer;

public class MessageTransformer extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		MessageProcesser thisMessage= new MessageProcesser();
		return thisMessage.processMessage(message);
	}

}
