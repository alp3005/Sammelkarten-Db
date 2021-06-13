package Cards;

public class Spell extends Card {
	private int type;
	private String effect;
	private int tag;
	
	public Spell(String name, int id, int type, String effect, int tag) {
		super(name, 2, id);
		this.type = type;
		this.effect = effect;
		this.tag = tag;
	}

	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getEffect() {
		return this.effect;
	}
	
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public int getTag() {
		return this.tag;
	}
	
	public void setTag(int t) {
		this.tag = t;
	}
	
}
