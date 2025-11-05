package world.entity.plant.Tree;

import org.newdawn.slick.Graphics;

import core.Game;
import core.Images;
import item.Axe;
import item.Item;
import world.Cell;

public class PineTree extends Tree {
    private static final int INITIAL_HEALTH = 8;
    private static final int VALUE = 1;
    public PineTree(int daysGrown, boolean fullyGrown) {
        super(daysGrown);
        

        if (fullyGrown) {
        } else {
        }

        
        sheet = Images.pineTree;
        setImage();
        setHealth();

        this.curHealth = INITIAL_HEALTH;
        this.maxHealth = INITIAL_HEALTH;
        this.value = VALUE;
    }


    @Override
    protected void remove() {

    }



	@Override
	public void nextDay() {
		 super.nextDay();
	}

    @Override
    public void clicked(Item selectedItem) {
        if (Game.hasStamina(STAMINA_COST) && selectedItem instanceof Axe) {
            Game.expendStamina(STAMINA_COST);
            takeDamage(DAMAGE_AMOUNT);
            if (curHealth <= 0) {

                cell.removeEntity();
                expired = true;
                Game.gainMoney(VALUE * maxHealth);
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
        
        damageTimer++;

        if (image != null) {
            if (damaged && damageTimer < DAMAGE_EFFECT_DURATION) {
            	
                image.setImageColor(1.0f, .6f, .4f);
            } else {
                image.setImageColor(1, 1, 1);
            }

            image.draw(cell.getX() * w, yPosition, w, scaledHeight);
        }
	
    }

	@Override
    public void setImage() {
        
            image = sheet.getSubImage(3, 0);
        
    }

}
