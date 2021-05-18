package Cards;

public class Trap extends Card {
	private int type;
	private String effect;
	private Tag[] tags;
	
	public Trap(String n, int id, int t, String e, Tag[] tag) {
		super(n, 3, id);
		this.type = t;
		this.effect = e;
		this.tags = tag;
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
	
	public Tag[] getTags() {
		return this.tags;
	}
	
	public void setTags(Tag[] t) {
		this.tags = t;
	}
	
}
