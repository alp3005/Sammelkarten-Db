package Cards;

public class Spell extends Card {
	private String type;
	private String effect;
	private String[] tags;
	
	public Spell(String name, int id, String type, String effect, String[] tags) {
		super(name, 2, id);
		this.type = type;
		this.effect = effect;
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
