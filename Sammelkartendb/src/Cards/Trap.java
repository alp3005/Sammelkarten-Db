package Cards;

public class Trap extends Card {
	private String type;
	private String effect;
	private String tag;
	
	public Trap(String n, int id, String type, String e, String tag) {
		super(n, 3, id);
		this.type = type;
		this.effect = e;
		this.tag = tag;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getEffect() {
		return this.effect;
	}
	
	public void setEffect(String e) {
		this.effect = e;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTags(String t) {
		this.tag = t;
	}
	
}
