package item;

import world.entity.Entity;

public abstract class Tool extends Item {

    public Tool(String imagePath, String name, int cost) {
        super(imagePath, name , cost);
    }
    
    @Override
    public void clicked(Item selectedItem) {
        
    }
    
    @Override
    public Entity makeEntity() {
        return null;
    }
}
