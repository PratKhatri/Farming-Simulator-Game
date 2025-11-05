package item;

import org.newdawn.slick.SlickException;

import world.entity.BasicSprinkler;

public class BasicSprinklerItem extends SprinklerItem {
	
	public BasicSprinklerItem() throws SlickException {
	    super("res/sprinklerBasicItem.png", "Basic Sprinkler", 20);
	}
	
	@Override
	public BasicSprinkler makeEntity() {
	    return null;
	}
	
	@Override
	public void clicked(Item selectedItem) {
	}
}