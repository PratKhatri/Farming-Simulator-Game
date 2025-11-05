package item;

import core.Images;

public class Bucket extends Tool {

    private int water;
    private int capacity;

    public Bucket() {
        super("res/bucketEmpty.png", "Bucket", 5);
        water = 0;
        capacity = 5;
    }

    public int getWater() {
        return water;
    }

    public int getCapacity() {
        return capacity;
    }

 
    public void fill() {
        water = capacity; 
        image = Images.fullBucket;
    }


    public void use() {
        if (water > 0) {
            water--;

            
            if (water == 0) {
                image = Images.emptyBucket;
            }
        }
    }

    @Override
    public void clicked(Item selectedItem) {
       
    }
}

