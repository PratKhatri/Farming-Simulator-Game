package world;

import core.Game;
import core.Main;
import item.Item;
import item.SprinklerItem;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import world.entity.BasicSprinkler;
import world.entity.Entity;
import world.entity.plant.crop.Crop;
import world.terrain.Terrain;

public class Cell {

	private int x;
	private int y;
	private Terrain terrain;
	private Entity entity;
	

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        setTerrain(new world.terrain.Dirt(true, false));

    }
    
    public void setEntity(Entity e) {
        entity = e;
        if (entity != null) {
            entity.setCell(this);
        }
    }
    
    public void removeEntity() 
    {
        if (entity != null) 
        {
            entity.setCell(null);
            entity = null;
        }
    }
    
    public Entity getEntity() {
        return entity;
    }

    public boolean hasEntity() {
        return entity != null;
    }
    
	public void setTerrain(Terrain t)
	{
		terrain = t;
		terrain.setCell(this);
	}
	
	public static int getWidth()
	{
		return Main.getScreenWidth() / world.World.WIDTH;
	}
	
	public static int getHeight()
	{
		return (Main.getScreenHeight() - 128) / World.HEIGHT;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Terrain getTerrain() {
		
		return terrain;
	}


	public void render(Graphics g) {
        terrain.render(g);

        if (entity != null) {
            entity.render(g);
        }

        //highlight the cell
        if (isMouseOver(Game.gc.getInput().getMouseX(), Game.gc.getInput().getMouseY())) {
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(getXPixel(), getYPixel(), Cell.getWidth(), Cell.getHeight());
        }

        if(entity != null && entity instanceof Crop && ((Crop) entity).isMature())
        {
        	g.setColor(Color.white);
        	g.setLineWidth(2);
        	g.drawRect(getX() * getWidth(), getY() * getHeight(), getWidth()-4, getHeight()-4);
        	g.resetLineWidth();
        }
    }

	public void clicked(Item selectedItem) {
	    if (selectedItem instanceof SprinklerItem && !hasEntity()) {
	        SprinklerItem sprinklerItem = (SprinklerItem) selectedItem;
	        BasicSprinkler sprinkler = sprinklerItem.makeEntity();
	        setEntity(sprinkler);
	        selectedItem.expire();
	    } else if (hasEntity() && entity.isFocus()) {
	        entity.clicked(selectedItem);
	    } else {
	        terrain.clicked(selectedItem);
	    }
	}


	
	public void nextDay() {
		terrain.nextDay();

	}
	
	public int getXPixel() {
		return x * Cell.getWidth();
	}
	
	public int getYPixel() {
		return y * Cell.getHeight();
	}

	public boolean isMouseOver(int mouseX, int mouseY) {
        int cellX = getXPixel();
        int cellY = getYPixel();
        int cellWidth = Cell.getWidth();
        int cellHeight = Cell.getHeight();

        return mouseX >= cellX && mouseX < cellX + cellWidth &&
               mouseY >= cellY && mouseY < cellY + cellHeight;
    }

	public ArrayList<Cell> addCellToList(ArrayList<Cell> list, int x, int y) {
	    Cell neighbor = World.getCell(x, y);
	    if (neighbor != null) {
	        list.add(neighbor);
	    }
	    return list;
	}

	public ArrayList<Cell> getFourNeighbors() {
	    ArrayList<Cell> neighbors = new ArrayList<>();
	    neighbors = addCellToList(neighbors, x, y - 1); //n
	    neighbors = addCellToList(neighbors, x, y + 1); //s
	    neighbors = addCellToList(neighbors, x - 1, y); //w
	    neighbors = addCellToList(neighbors, x + 1, y); //e
	    return neighbors;
	}


}
