package Cards;

public class Trap extends Card {
	private int type;
	private String effect;
	private int tag;
	
	public Trap(String n, int id, int t, String e, int tag) {
		super(n, 3, id);
		this.type = t;
		this.effect = e;
		this.tag = tag;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int t) {
		this.type = t;
	}
	
	public String getEffect() {
		return this.effect;
	}
	
	public void setEffect(String e) {
		this.effect = e;
	}
	
	public int getTag() {
		return this.tag;
	}
	
	public void setTags(int t) {
		this.tag = t;
	}
	
}
