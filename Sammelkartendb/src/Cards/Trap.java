package Cards;

public class Trap extends Card {
	private String type;
	private String effect;
	private String[] tags;
	
	public Trap(String n, int id, String type, String e, String[] tags) {
		super(n, 3, id);
		this.type = type;
		this.effect = e;
		this.tags = tags;
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
	
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public String[] getTags() {
		return this.tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
}
