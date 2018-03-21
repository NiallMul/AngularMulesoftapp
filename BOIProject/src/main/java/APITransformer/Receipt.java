package APITransformer;

import java.util.LinkedList;

public class Receipt {
private LinkedList<ReceiptLine> lines;
	
	public Receipt() {
		lines = new LinkedList<ReceiptLine>();
	}
	
	public LinkedList<ReceiptLine> getLines()
	{
		return lines;
	}
	
	public void sortAllLines()
	{
		for(ReceiptLine line : lines)
		{
			line.sortLine();
		}
	}
	
	public void insertElement(String word, int leftXPos, int topYPos)
	{
		boolean lineInserted = false;
		
		int boundingBuffer = 10;
		
		if(!lines.isEmpty())
		{
			for(int i =0; i < lines.size(); i++)
			{
				if(lines.get(i).getyPos() > topYPos - boundingBuffer && lines.get(i).getyPos() < topYPos + boundingBuffer)
				{
					lines.get(i).insertWord(word, leftXPos);
					lineInserted = true;
					break;
				}
			}
			if(!lineInserted)
			{
				lines.addLast(new ReceiptLine(topYPos));
				lines.getLast().insertWord(word, leftXPos);
			}
		}
		else
		{
			lines.addFirst(new ReceiptLine(topYPos));
			lines.get(0).insertWord(word, leftXPos);
			lineInserted = true;
		}
		
	}
}
