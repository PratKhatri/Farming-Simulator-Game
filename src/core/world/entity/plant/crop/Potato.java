package world.entity.plant.crop;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import core.Images;
import item.Item;
import world.Cell;
import world.terrain.Dirt;
import world.terrain.Terrain;

public class Potato extends Crop {
    public Potato() {
        maturity = 4;
        value = 3;
        sheet = Images.potato;
        setImage();
    }
    
    @Override
    public void setImage() {
        if (daysGrown == 0) {
            image = sheet.getSubImage(0, 0);
        } else if (percentMaturity() < 0.5) {
            image = sheet.getSubImage(1, 0);
        } else if (percentMaturity() < 1) {
            image = sheet.getSubImage(2, 0);
        } else {
            image = sheet.getSubImage(3, 0);
        }
    }

    @Override
    public void render(Graphics g) {
        int w = Cell.getWidth();
        int h = Cell.getHeight();

        float yPosition = cell.getY() * h - h / 2;
        float scaledHeight = h * 1.5f;

        if (image != null) {
            image.draw(cell.getX() * w, yPosition, w, scaledHeight);
        }

        super.render(g);
    }

    
    
    
    @Override
    public boolean isValid(Terrain t) {
        return t instanceof Dirt;
    }

    @Override
    public void nextDay() {
        super.nextDay();

        if (isMature()) {
            focus = true;
        }
    }
    
    @Override
	 public void clicked(Item selectedItem) {
	     if (isMature()) {
	         
	         cell.setEntity(null);
	         expired = true;
	         Game.gainMoney(value);
	     }
	 }
}
