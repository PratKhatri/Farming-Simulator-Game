package world.terrain;

import core.Game;
import core.Images;
import item.Bucket;
import item.CropSeed;
import item.Hoe;
import item.Item;
import world.World;
import world.weather.Weather;
import world.entity.Entity;
import world.entity.plant.crop.Crop;

public class Dirt extends Terrain {

    public boolean soil;
    public boolean wet;

    public Dirt(boolean soil, boolean wet) {
        this.soil = soil;
        this.wet = wet;
        setWeatherWet();
        setImage();
    }

    public void setImage() {
        if (soil && wet) {
            image = Images.wetSoil;
        } else if (soil) {
            image = Images.drySoil;
        } else {
            image = Images.dirt;
        }
    }

    @Override
    public void nextDay() {
        wet = false;
        setWeatherWet();
        setImage();
    }

    public void setWeatherWet() {
        wet = Weather.isRaining();
    }

    public boolean isSoil() {
        return soil;
    }

    public boolean isWet() {
        return wet;
    }
    
    public void waterSoil() {
    	wet = true;
    	setImage();
    }

    @Override
    public void clicked(Item selectedItem) {
        if (Game.hasStamina(10)) {
            if (soil && !cell.hasEntity() && selectedItem instanceof CropSeed) {
                Crop c = (Crop) ((CropSeed) selectedItem).makeEntity();
                World.addEntity(c, cell.getX(), cell.getY());
                Game.expendStamina(10);
                selectedItem.expire();
            } else if (!soil && selectedItem instanceof Hoe) {
                soil = true;
                Game.expendStamina(10);
                setImage();
            } else if (soil && !wet && selectedItem instanceof Bucket) {

                if (((Bucket) selectedItem).getWater() > 0) {
                    wet = true;
                    Game.expendStamina(10);
                    setImage();
                    ((Bucket) selectedItem).use();
                }
            }
        }
        setImage();
    }
}
