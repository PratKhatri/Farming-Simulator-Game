package item;

import world.entity.Entity;
import world.entity.plant.crop.Corn;

public class CornSeed extends CropSeed {

    public CornSeed() {
        super("res/cornSeed.png", "Corn Seed", 2);
    }

    @Override
    public Entity makeEntity() { 
        return new Corn();
    }

	@Override
	public void clicked(Item selectedItem) {
		
		// TODO Auto-generated method stub
		
	}
}
