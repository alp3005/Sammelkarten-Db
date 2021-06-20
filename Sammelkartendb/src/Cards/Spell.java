package Cards;

public class Spell extends Card {
	private String type;
	private String effect;
	private String tag;
	
	public Spell(String name, int id, String type, String effect, String tag) {
		super(name, 2, id);
		this.type = type;
		this.effect = effect;
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
	
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String t) {
		this.tag = t;
	}
	
}
