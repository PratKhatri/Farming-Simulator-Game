package world.terrain;

import core.Game;
import core.Images;
import item.Bucket;
import item.Item;

public class Water extends Terrain {

    public Water() {
        image = Images.water;
    }
    public void clicked() {
    	
    }
    public void nextDay() {
    	
    }
	@Override
	public void clicked(Item selectedItem) {
		if (selectedItem instanceof Bucket && Game.hasStamina(1)) {
			((Bucket) selectedItem).fill();

            Game.expendStamina(10);
        }
	}
}

