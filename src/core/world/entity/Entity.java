package world.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import item.Item;
import world.Cell;
import world.terrain.Terrain;

public abstract class Entity {
    protected Cell cell;
    protected Image image;
    protected int value;
    protected boolean expired;
    protected boolean focus;
    
    protected int curHealth;
    protected int maxHealth;
    
    protected boolean damaged;
    protected float damageTimer;
    protected static final float DAMAGE_EFFECT_DURATION = 20;

    public void setCell(Cell c) {
        cell = c;
    }
    
    
    public abstract boolean isValid(Terrain t);
    public abstract void clicked(Item selectedItem);
    public abstract void nextDay();
    
    public void takeDamage(int amount) {
    	curHealth -= amount;
        damaged = true;
        damageTimer = 0;
        if (curHealth < 0) {
            curHealth = 0;
        }
        
    }
    
    public void render(Graphics g) {
        int w = Cell.getWidth();
        int h = Cell.getHeight();
        image.draw(cell.getX() * w, cell.getY() * h - h / 2, w, h * 1.5f);
        
        damageTimer++;
        
        if (damaged && damageTimer < DAMAGE_EFFECT_DURATION) {

            image.setImageColor(1.0f, .6f, .4f);
        } else {

            image.setImageColor(1, 1, 1);
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isExpired() {
        return expired;
    }
    
    public boolean isFocus() {
        return focus;
    }  
}
