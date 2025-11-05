package world.entity.plant.Tree;

import org.newdawn.slick.Graphics;

import core.Game;
import item.Item;
import world.Cell;
import world.entity.plant.Plant;
import world.terrain.Grass;
import world.terrain.Terrain;

public abstract class Tree extends Plant {

    protected static final int INITIAL_HEALTH = 8;
    protected static final int STAMINA_COST = 5;
    protected static final int DAMAGE_AMOUNT = 1;


    public Tree(int daysGrown) {
        super();
        focus = true;
        setHealth();
    }

    @Override
    public boolean isValid(Terrain t) {
        return t instanceof Grass;
    }

    protected void setHealth() {
        curHealth = INITIAL_HEALTH + (daysGrown * 20);
        curHealth = maxHealth;
    }

    @Override
    public void nextDay() {

    	 daysGrown++;
    	 setImage();

    }

    @Override
    public void clicked(Item selectedItem) {
        if (Game.hasStamina(STAMINA_COST)) {
        	
            Game.expendStamina(STAMINA_COST);
            takeDamage(DAMAGE_AMOUNT);
            
            if (curHealth <= 0) {

                remove();
                Game.gainMoney(value * maxHealth);
            } else {
            	damaged = true;
            	damageTimer = 0;
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        int w = Cell.getWidth();
        int h = Cell.getHeight();

        float yPosition = cell.getY() * h - h / 2;
        float scaledHeight = h * 1.5f;

        if (image != null) {
            if (damaged && damageTimer < DAMAGE_EFFECT_DURATION) {
            	
                image.setImageColor(1.0f, 0f, 0f);
            } else {
            	
                image.setImageColor(1, 1, 1);
            }

            image.draw(cell.getX() * w, yPosition, w, scaledHeight);
        } 


    }
    
    protected abstract void remove();

}
