package world.terrain;

import org.newdawn.slick.Image;

import core.Images;
import item.Item;

import org.newdawn.slick.Graphics;

import world.Cell;

public abstract class Terrain {

    protected Image image;
    protected Cell cell;

    public Terrain() {
        image = Images.gray;
        
    }

    public void setCell(Cell c) {
        cell = c;
    }

    public void render(Graphics g) {
        int w = Cell.getWidth();
        int h = Cell.getHeight();

        g.drawImage(image, cell.getX() * w, cell.getY() * h);

    }
    
    public abstract void nextDay();
    public abstract void clicked(Item selectedItem);

}
