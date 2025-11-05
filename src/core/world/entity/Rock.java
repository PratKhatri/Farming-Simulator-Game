package world.entity;

import item.CropSeed;
import item.Item;
import item.Pick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Game;
import world.Cell;
import world.terrain.Terrain;

public class Rock extends Entity {
    private static final int INITIAL_HEALTH = 5;
    private static final int VALUE = 1;

    public Rock() throws SlickException {
       
        this.focus = true;
        this.image = new Image("res/rock.png");

        this.curHealth = INITIAL_HEALTH;
        this.maxHealth = INITIAL_HEALTH;
        this.value = VALUE;
        
        
    }
    
    @Override
    public void takeDamage(int amount) {
        curHealth -= amount;
        if (curHealth < 0) {
            curHealth = 0;
        }
        
    }

    @Override
    public boolean isValid(Terrain t) {
        return true;
    }

    @Override
    public void clicked(Item selectedItem) {
       
        if (Game.hasStamina(1) && selectedItem instanceof Pick) {
            Game.expendStamina(5);
            curHealth--;

            if (curHealth <= 0) {
                cell.removeEntity();
                expired = true;
                Game.gainMoney(value);
            } else {
            	damaged = true;
            	damageTimer = 0;
            }
        }
        
    }

    @Override
    public void nextDay() {
    }

    @Override
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
}
