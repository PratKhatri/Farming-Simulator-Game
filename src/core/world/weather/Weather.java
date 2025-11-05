package world.weather;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import core.Main;

public class Weather {
    private static boolean raining;
    private static ArrayList<Raindrop> raindrops;

    public Weather() {
        raining = false;
    }


    public static boolean isRaining() {
        return raining;
    }

    public static void nextDay() {
        //no rain day 1-2
        if (Game.getDays() <= 2) {
            raining = false;
        } else {
            //1/5 chance of rain
            Random rand = new Random();
            raining = rand.nextInt(5) == 0;
        }
        
        if (raining) {
            createRaindrops();
        }
    }

    public static void render() {
        if (raining) {
            renderRain();
        }
    }

    private static void createRaindrops() {
    	raindrops = new ArrayList<>();

        int numRaindrops = 100;
        Random random = new Random();

        for (int i = 0; i < numRaindrops; i++) {
            float x = random.nextInt(Main.getScreenWidth());
            float y = random.nextInt(Main.getScreenHeight());
            float speed = random.nextFloat() * 12 + 3;
            float length = random.nextFloat() * 20 + 5;
            float width = random.nextFloat() * 4 + 2;
            raindrops.add(new Raindrop(x, y, speed, length, width));
        }
    }
    
    private static void renderRain() {
        Graphics g = Game.gc.getGraphics();

        g.setColor(new Color(0, 0, 255, 100));
        for (Raindrop raindrop : raindrops) {
            raindrop.update();
            raindrop.render(g);
        }
    }

}