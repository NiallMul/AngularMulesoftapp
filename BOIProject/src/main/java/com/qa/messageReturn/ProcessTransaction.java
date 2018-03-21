package com.qa.messageReturn;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

public class ProcessTransaction extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		Transaction transaction = (Transaction) message.getPayload();
		message.setProperty("transactionId", transaction.getId(), PropertyScope.SESSION);
		System.out.println(transaction.getId());
		return message;
	}

}
