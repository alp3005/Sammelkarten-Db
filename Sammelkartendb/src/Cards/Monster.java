package Cards;

public class Monster extends Card {
	private int attribute;
	private int lvl;
	private int atk;
	private int def;

	public Monster(String n, int id, int a, int l, int atk, int def) {
		super(n, 1, id);
		this.attribute = a;
		this.lvl = l;
		this.atk = atk;
		this.def = def;
	}

	public int getAttribute() {
		return this.attribute;
	}

	public void setAttribute(int a) {
		this.attribute = a;
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
