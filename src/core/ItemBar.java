package core;

import item.Item;
import item.Bucket;
import item.CornSeed;
import item.Hoe;
import item.PotatoSeed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ItemBar {
    private List<Item> items;
    private int selectedIndex = -1;
    private static final int MAX_ITEMS = 9;

    //constructor
    public ItemBar() {
        items = new ArrayList<>();
        items.add(new Hoe());
        items.add(new Bucket());
        selectedIndex = -1;
    }


    public void render(Graphics g, int screenHeight) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.isExpired()) {
                iterator.remove();
            } else {
                item.render(g, items.indexOf(item), screenHeight, items.indexOf(item) == selectedIndex);
            }
        }
    }
    
    public void expireItems() {
        for (Item item : items) {
            if (item.isExpired()) {
                item.expire();
            }
        }
    }
    
    public boolean hasSelectedItem() {
        return selectedIndex >= 0 && selectedIndex < items.size();
    }

    public Item getSelectedItem() {
        if (hasSelectedItem()) {
            return items.get(selectedIndex);
        }
        return null;
    }

    public void clearSelection() {
        selectedIndex = -1;
    }
    
    public void keyPressed(int key) {
      
        int index = key - Input.KEY_1;

        if (index >= 0 && index < items.size()) {
            clearSelection();
            selectedIndex = index;
        } else {
            clearSelection();
        }
    }

    
    public void setCursor() {
        if (!hasSelectedItem()) {
            Game.gc.setDefaultMouseCursor();
        } else {
            try {
            	Item selectedItem = getSelectedItem();
                Image selectedItemImage = selectedItem.getImage().getScaledCopy(32, 32);
                Game.gc.setMouseCursor(selectedItemImage, 16, 16);


                int selectedIndex = items.indexOf(selectedItem);
                int x = selectedIndex * 80; //spacing
                int y = Game.gc.getScreenHeight() - 100; 

                Game.gc.getGraphics().setColor(org.newdawn.slick.Color.white);
                Game.gc.getGraphics().drawRect(x, y, 80, 80);
                if (selectedItem.isExpired()) {
                    clearSelection();
                } else {
                   
                   
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
    
 
    
    public void cleanup()
    {
    	for (int i = 0; i < items.size(); i++)
    	{
    		if(items.get(i).isExpired())
    		{
    			if(selectedIndex == i)
    			{
    				selectedIndex = -1;
    				setCursor();
    			}
    			items.remove(i);
    			i--;
    		}
    	}
    }
    
    public boolean hasSpace() {
    	return items.size() < MAX_ITEMS;
    }
    
    public void addItem(Item item) {
        if (hasSpace()) {
            items.add(item);
        }
    }
    
    public void mousePressed(int button, int x, int y) {
    	setCursor();
    }
}
