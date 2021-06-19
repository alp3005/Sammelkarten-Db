package Cards;

public class Monster extends Card {
	private String element;
	private int lvl;
	private int atk;
	private int def;

	public Monster(String n, int id, String attr, int l, int atk, int def) {
		super(n, 1, id);
		this.element = attr;
		this.lvl = l;
		this.atk = atk;
		this.def = def;
	}

	public String getAttribute() {
		return this.element;
	}

	public void setAttribute(String a) {
		this.element = a;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void setLvl(int l) {
		this.lvl = l;
	}

	public int getAtk() {
		return this.atk;
	}

	public void setAtk(int a) {
		this.atk = a;
	}

	public int getDef() {
		return this.def;
	}

	public void setDef(int d) {
		this.def = d;
	}

}
