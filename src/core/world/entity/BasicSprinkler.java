package world.entity;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import item.Item;
import world.Cell;
import world.entity.Sprinkler;
import world.terrain.Dirt;
import world.terrain.Terrain;

public class BasicSprinkler extends Sprinkler {
	
	private Image image;
    private int cellX;
    private int cellY;
	
	public BasicSprinkler(int cellX, int cellY) {
        try {
            image = new Image("res/sprinklerBasicEntity.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        
        this.cellX = cellX;
        this.cellY = cellY;
    }

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void clicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waterCells(ArrayList<Cell> neighbors) {
        
        for (Cell neighbor : neighbors) {
            if (neighbor != null) {
                Terrain terrain = neighbor.getTerrain();
                if (terrain instanceof Dirt) {
                    ((Dirt) terrain).waterSoil();
                }
            }
        }
    }

	@Override
	public boolean isValid(Terrain t) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void clicked(Item selectedItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextDay() {
		waterCells(cell.getFourNeighbors());
		
	}
	
	
	 @Override
	    public void render(Graphics g) {
	        if (image != null) {
	            float x = cellX * Cell.getWidth();
	            float y = cellY * Cell.getHeight();
	            image.draw(x, y, Cell.getWidth(), Cell.getHeight());
	        }
	    }


}

