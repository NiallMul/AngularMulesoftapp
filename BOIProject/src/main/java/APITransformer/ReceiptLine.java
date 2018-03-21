package APITransformer;

import java.util.LinkedList;



public class ReceiptLine {
	private LinkedList<ValuePair> words;
	private int yPos = 0;
	private String productName = "";
	private String price = "";
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public ReceiptLine(int yPos) {
		this.yPos = yPos;
		words = new LinkedList<ValuePair>();
	}
	
	public LinkedList<ValuePair> getWords()
	{
		return words;
	}
	
	public void sortLine()
	{
		productName="";
		for(ValuePair valPair : words)
		{
			if(valPair != words.getLast())
			{
				productName += " " + valPair.getWord();
			}
			else
			{
				price = valPair.getWord();
			}
		}
	}
	
	public void insertWord(String word, int leftXPos)
	{
		boolean wordInserted = false;
		ValuePair inputVal = new ValuePair(leftXPos,word);
		if(!words.isEmpty())
		{
			for(int i =0; i < words.size(); i++)
			{
				if(words.get(i).getXPos() > leftXPos)
				{
					words.add(i,inputVal);
					wordInserted = true;
					break;
				}
			}
			if(!wordInserted)
			{
				words.addLast(inputVal);
			}
		}
		else
		{
			words.addFirst(inputVal);
		}
		
	}
}
