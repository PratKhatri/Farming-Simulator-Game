package item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import world.entity.Entity;

public abstract class Item {
	
	protected Image image;
	protected boolean expired;
	private String name;
	private int cost;
	protected boolean unique;

	public Item(String imagePath, String name, int cost) {
		try {
			image = new Image(imagePath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.cost = cost;
		expired = false; 
		unique = false;
	}
	
	 public String getName() {
	        return name;
	    }

	 public int getCost() {
	        return cost;
	    }
	 
	 public boolean isUnique() {
		 	return unique;
	 }
	    
	
	public void render(Graphics g, int index, int screenHeight, boolean isSelected) {
	    int x = index * 80; //spacing
	    int y = screenHeight - 100; 


	    if (isSelected) {
	        g.setColor(Color.white);
	        g.drawRect(x, y, 80, 80);
	    }

	    
	    image.draw(x, y, 80, 80);

	    
	    if (index < 9) {
	    	g.setColor(new Color(255, 255, 255));
	        g.drawString(Integer.toString(index + 1), x + 20, y - 20);
	    }
	    
	}
	
	public Image getImage() {
        return image;
    }
	
	public abstract void clicked(Item selectedItem);

	public boolean isExpired() {
		return expired;
	}

	public void expire() {
		expired = true;
	}

	 public abstract Entity makeEntity();
}
