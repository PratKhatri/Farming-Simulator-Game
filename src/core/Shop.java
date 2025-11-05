package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import item.Axe;
import item.BasicSprinklerItem;
import item.Bucket;
import item.CornSeed;
import item.Hoe;
import item.Item;
import item.Pick;
import item.PotatoSeed;
import item.SprinklerItem;

public class Shop extends BasicGameState {

    private Image shopBackground;
    private StateBasedGame sbg;
    private ArrayList<Item> items;


    public Shop(int state) {

    }

    @Override
    public int getID() {
        return Main.SHOP_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        shopBackground = new Image("res/shop.png");
        this.sbg = sbg;

        items = new ArrayList<>();
        items.add(new CornSeed());
        items.add(new PotatoSeed());
        items.add(new Pick());
        items.add(new Axe());
        items.add(new BasicSprinklerItem());
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        shopBackground.draw(0, 0, gc.getWidth(), gc.getHeight());

        renderMoney(g);
        renderDays(g);
        renderStoreItems(g);
        Game.getItemBar().render(g, gc.getScreenHeight());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {


    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_E) {
            sbg.enterState(Main.GAME_ID);
        }

        if (key >= Input.KEY_1 && key <= Input.KEY_9) {
            int index = key - Input.KEY_1;

            //check if index is within the bounds of items ArrayList
            if (index >= 0 && index < items.size()) {
                Item original = items.get(index);
                int cost = original.getCost();

                if (Game.hasMoney(cost) && Game.getItemBar().hasSpace()) {

                    Item newItem = buildItem(original.getClass());
                    buy(newItem);

                    if (original.isUnique()) {
                        items.remove(original);
                    }

                } else {
                    System.out.println("no money/no space in itembar");
                }
            }
        }
    }

    private void renderMoney(Graphics g) {
        int moneyX = 10;
        int moneyY = 35;

        Fonts.big.drawString(moneyX + 2, moneyY + 2, "$" + Game.getMoney(), Color.black);

        Fonts.big.drawString(moneyX, moneyY, "$" + Game.getMoney(), Color.white);

        g.setColor(new Color(255, 255, 0));
        int scoreX = 10;
        int scoreY = 120;
        g.drawString("Score: " + Game.getScore(), scoreX, scoreY);
    }

    private void renderDays(Graphics g) {
        int daysX = 1600;
        int daysY = 35;

        Fonts.big.drawString(daysX + 2, daysY + 2, "Days: " + Game.getDays(), Color.black);

        Fonts.big.drawString(daysX, daysY, "Days: " + Game.getDays(), Color.white);
    }

    private void renderStoreItems(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int x = 50;
            int y = 150 + i * 50;
            g.drawString((i + 1) + ". " + item.getName() + " - $" + item.getCost(), x, y);
            g.setColor(new Color(255, 255, 255));
        }
    }

    public Item buildItem(Class<? extends Item> clazz) {
        Item i = null;

        try
        {
            i = clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return i;
    }

    public void buy(Item i) {

        int cost = i.getCost();

        if (Game.hasMoney(cost)) {

            Game.spendMoney(cost);

            Game.getItemBar().addItem(i);
        } else {
            System.out.println("Not enough money to buy " + i.getName());
        }
    }

}

