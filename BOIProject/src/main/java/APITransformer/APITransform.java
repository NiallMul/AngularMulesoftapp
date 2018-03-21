package APITransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

public class APITransform extends AbstractMessageTransformer{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		HashMap<String,ArrayList> speficData=(HashMap<String,ArrayList>) message.getPayload();

		ArrayList al = speficData.get("responses");
		speficData = (HashMap<String,ArrayList>)al.get(0);
		
		al = speficData.get("textAnnotations");//all elements including the first array describing the while doc
		Receipt currentReceipt = new Receipt();
		for(int i = 1; i < al.size(); i++)//skip the first as it contains the description
		{//each element will contain a description(the current word) and a boundingPolly which contains an element "vertices" which includes an array of 4 x y coordinates
			ValuePair currentWord = new ValuePair(0, "");
			
			HashMap<String,Object> anElement = (HashMap<String,Object>)al.get(i);
			for (HashMap.Entry<String,Object> entry : anElement.entrySet()) {
				if(entry.getKey()=="description")
				{
					String val = (String.valueOf(entry.getValue()));
					currentWord.setWord(val);
				}
				else if(entry.getKey()=="boundingPoly")
				{
					LinkedHashMap<String, ArrayList> vertices = (LinkedHashMap<String, ArrayList>)entry.getValue();
					ArrayList boxPoints = vertices.get("vertices");
					
					
					LinkedHashMap<String,Integer> p1 =  (LinkedHashMap<String, Integer>) boxPoints.get(0);
					java.awt.Point tl = new java.awt.Point(p1.get("x"),p1.get("y"));
					p1 =  (LinkedHashMap<String, Integer>) boxPoints.get(1);
					java.awt.Point tr = new java.awt.Point(p1.get("x"),p1.get("y"));
					p1 =  (LinkedHashMap<String, Integer>) boxPoints.get(2);
					java.awt.Point br = new java.awt.Point(p1.get("x"),p1.get("y"));
					p1 =  (LinkedHashMap<String, Integer>) boxPoints.get(3);
					java.awt.Point bl = new java.awt.Point(p1.get("x"),p1.get("y"));
					//ystem.out.println("bl point = " + bl);
					currentWord.setXPos(tl.x);
					currentReceipt.insertElement(currentWord.getWord(), currentWord.getXPos(), tl.y);
				}
			}
		}
		printSortedLines(currentReceipt);
		currentReceipt.sortAllLines();
		
		message.setProperty("receipt", currentReceipt, PropertyScope.SESSION);
		
		return message;
	}
	
	public void printReceipt(Receipt currentReceipt)
	{
		for(ReceiptLine currentLine : currentReceipt.getLines())
		{
			String line = "";
			for(ValuePair word : currentLine.getWords())
			{
				line += " " + word.getWord();
			}
			System.out.println(line);
		}
	}

	public void printSortedLines(Receipt currentReceipt)
	{
		currentReceipt.sortAllLines();
		for(ReceiptLine currentLine : currentReceipt.getLines())
		{
			
			System.out.println("product = " + currentLine.getProductName());
			System.out.println("Price = " + currentLine.getPrice());
		}
	}
}
