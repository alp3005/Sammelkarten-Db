package Cards;

public class Card {
	private String name;
	private int kategory;
	private int id;
	
	public Card(String n, int k, int id) {
		this.name = n;
		this.kategory = k;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKategory() {
		return kategory;
	}
	
	public void setKategory(int kategory) {
		this.kategory = kategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
