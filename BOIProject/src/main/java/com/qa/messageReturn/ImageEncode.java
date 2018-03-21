package com.qa.messageReturn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

public class ImageEncode extends AbstractMessageTransformer{
		@Override
		public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
			String imageMessage = message.getPayload().toString();
			String[] imageList = imageMessage.split("=");
			imageMessage = imageList[1];
			imageMessage = imageMessage.substring(0,imageMessage.length()-2);
			System.out.println(imageMessage);
			File file = new File(imageMessage);
			
			try (FileInputStream imageInFile = new FileInputStream(file)) {
				byte imageData[] = new byte[(int) file.length()];
				imageInFile.read(imageData);
				imageMessage = Base64.getEncoder().encodeToString(imageData);
				
				message.setProperty("img", imageMessage, PropertyScope.SESSION);
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading the Image " + ioe);
			}
			return message;
		}
}
