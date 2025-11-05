package item;

import world.entity.BasicSprinkler;

public abstract class SprinklerItem extends Item {
	
	public SprinklerItem(String imagePath, String name, int cost) {
        super(imagePath, name, cost);
    }
	@Override
    public abstract BasicSprinkler makeEntity();

    @Override
    public void clicked(Item selectedItem) {
        
        if (selectedItem instanceof SprinklerItem) {
            
        }
    }
}
