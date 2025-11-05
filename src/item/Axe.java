package item;

public class Axe extends Tool {

	public Axe() {
		super("res/axe.png", "Axe", 10);
		unique = true;
	}
	
	@Override
    public void clicked(Item selectedItem) {
      
    }

}
