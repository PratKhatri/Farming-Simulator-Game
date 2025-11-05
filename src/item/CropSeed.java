package item;

import world.entity.Entity;

public abstract class CropSeed extends Seed {

    public CropSeed(String imagePath, String name, int cost) {
        super(imagePath, name, cost);
    }

    public abstract Entity makeEntity();

}
