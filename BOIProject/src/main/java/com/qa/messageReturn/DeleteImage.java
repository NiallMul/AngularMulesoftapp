package com.qa.messageReturn;

import java.io.File;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class DeleteImage extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		String imageMessage = message.getPayload().toString();
		String[] imageList = imageMessage.split("=");
		imageMessage = imageList[1];
		imageMessage = imageMessage.substring(0,imageMessage.length()-2);
		System.out.println(imageMessage);
		File file = new File(imageMessage);
		file.delete();
		
		return message;
	}

}
