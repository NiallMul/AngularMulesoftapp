package APITransformer;

public class ValuePair {
	private int xPos;
	private String word;
	
	ValuePair(int xPos, String word)
	{
		this.xPos = xPos;
		this.word = word;
	}
	public int getXPos() {
		return xPos;
	}
	public void setXPos(int left) {
		this.xPos = left;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String right) {
		this.word = right;
	}

}
