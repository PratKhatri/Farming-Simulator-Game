package item;

import world.entity.Entity;
import world.entity.plant.crop.Potato;

public class PotatoSeed extends CropSeed {

    public PotatoSeed() {
        super("res/potatoSeed.png", "Potato Seed", 1);
    }

    @Override
    public Entity makeEntity() { 
        return new Potato();
    }

	@Override
	public void clicked(Item selectedItem) {
		// TODO Auto-generated method stub
		
	}
}
//public void mousePressed(int button, int x, int y)
//{
//	world.mousePressed(button, x, y, items.getSelectedItems());
//	items.mousePressed(button, x, y);
//}