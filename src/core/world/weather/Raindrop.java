package world.weather;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Main;

public class Raindrop {
    private float x;
    private float y;
    private float speed;
    private float length;
    private float width;

    public Raindrop(float x, float y, float speed, float length, float width) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.length = length;
        this.width = width;
    }

    public void update() {
        y += speed;
        
        if (y > Main.getScreenHeight() - 170) {
            y = 0;
        }
    }

    public void render(Graphics g) {
    	Color raindropColor = new Color(0, 0, 255, 100);

        g.setColor(raindropColor);
        g.setLineWidth(width);
        g.drawLine(x, y, x, y + length);
        g.resetLineWidth();
    }
}
