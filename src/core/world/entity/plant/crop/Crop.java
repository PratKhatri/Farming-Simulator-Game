package world.entity.plant.crop;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import item.Item;
import world.entity.plant.Plant;
import world.terrain.Dirt;
import world.terrain.Terrain;

public abstract class Crop extends Plant {
	

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isValid(Terrain t)
	{
		return t instanceof Dirt && ((Dirt) t).isSoil();
	}
	
	 @Override
	    public void nextDay() {

	        Dirt d = (Dirt) cell.getTerrain();

	        if (d.isSoil() && d.isWet()) {
	            daysGrown++;
	            setImage();
	        }
	    }
	 public abstract void setImage();
	 
	 @Override
	 public void clicked(Item selectedItem) {
		 
	     if (isMature()) {

	         cell.setEntity(null);

	         expired = true;

	         Game.gainMoney(value);
	     }
	 }

}
