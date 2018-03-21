package com.qa.messageReturn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

public class ProcessImage extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		String imageMessage = message.getPayload().toString();
		imageMessage = imageMessage.substring(imageMessage.indexOf(",")+1);
		message.setProperty("receiptImage", imageMessage,  PropertyScope.SESSION);
		try {
			String abPath = "/src/main/images";
			String dir = System.getProperty("user.dir");
			System.out.println("old "+dir);
			String correctDir = dir.replace("\\", "/");
			System.out.println("new "+correctDir);
			String path= correctDir+abPath;
			
			File f = new File(path);
			File[] files = f.listFiles();
			String imgname = files.length+".png";
			String fPath=(path+"/newreceipt"+imgname);
			
        	OutputStream stream = new FileOutputStream(fPath);
        	stream.write( Base64.decodeBase64(imageMessage));
        	message.setProperty("imgPath", (fPath), PropertyScope.SESSION);
        	
        	stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return message;
	}

}
