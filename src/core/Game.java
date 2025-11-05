package core;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.ItemBar;
import item.Item;
import world.Cell;
import world.World;

public class Game extends BasicGameState
{
	private int id;
	private World world;
	private static int money = 50;
	private static ItemBar itemBar;
	public static int days = 1;
	private int selectedItemKey = -1;
	private static int score = 0;

	//stamina
	private static int curStamina;
	private static int maxStamina;
	public static int BASE_STAMINA = 100;
	private StateBasedGame sbg;


	public static GameContainer gc;

	public Game(int id, StateBasedGame sbg)
	{
		this.id = id;
		this.sbg = sbg;

	}

	public int getID()
	{
		return id;
	}



	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		//This code happens when you enter a game state for the *first time.*
		Images.loadImages();
		Fonts.loadFonts();
		gc.setShowFPS(true);
		world = new World();
		itemBar = new ItemBar();

		Game.gc = gc;

		curStamina = BASE_STAMINA;
		maxStamina = BASE_STAMINA;

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		//This is updates your game's logic every frame.  NO DRAWING.
		world.cleanup();
		itemBar.cleanup();
		itemBar.setCursor();
		//world.nextDay();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		world.render(g);

		renderMoney(g);

		itemBar.render(g, gc.getScreenHeight());

		renderStamina(g);

		renderDays(g);
	}


	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
	{

	}

	public void leave(GameContainer gc, StateBasedGame sbg)
	{

	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SPACE) {
			world.nextDay();
			days++;
			curStamina = maxStamina;
		}


		if (key == Input.KEY_E) {
			sbg.enterState(Main.SHOP_ID);
			itemBar.clearSelection();
			itemBar.setCursor();
		}


		if (key == selectedItemKey) {
			itemBar.clearSelection();
			selectedItemKey = -1;
			return;
		}

		itemBar.keyPressed(key);


		selectedItemKey = key;

		if (key < Input.KEY_1 || key > Input.KEY_9) {
			itemBar.clearSelection();
			selectedItemKey = -1;
		}
	}

	public void mousePressed(int button, int x, int y)
	{
		world.mousePressed(button, x, y, itemBar.getSelectedItem());
	}


	public static void gainMoney(int amount) {
		money += amount;
		score += amount;
	}

	public static int spendMoney(int amount) {
		if (hasMoney(amount)) {
			money -= amount;
			return amount;
		}
		return 0;
	}

	public static boolean hasMoney(int amount) {
		return money >= amount;
	}

	public void renderMoney(Graphics g) {
		int moneyX = 10;
		int moneyY = 35;

		Fonts.big.drawString(moneyX + 2, moneyY + 2, "$" + money, Color.black);

		Fonts.big.drawString(moneyX, moneyY, "$" + money, Color.white);
		g.setColor(new Color(255, 255, 0));
		int scoreX = 10;
		int scoreY = 120;
		g.drawString("Score: " + getScore(), scoreX, scoreY);
	}

	public static boolean hasStamina(int amount) {
		return curStamina >= amount;
	}

	public static float getPercentStamina() {
		return (float) curStamina / maxStamina;
	}

	public static void expendStamina(int amount) {
		if (hasStamina(amount)) {
			curStamina -= amount;
		}
	}

	public void renderStamina(Graphics g) {
		int screenWidth = Main.getScreenWidth();
		int staminaBarHeight = 10;

		//dark blue background bar
		g.setColor(new Color(0, 0, 100));
		g.fillRect(0, Cell.getHeight() * World.HEIGHT, screenWidth, staminaBarHeight);

		float percentStamina = getPercentStamina();

		//color by percent
		if (percentStamina == 1.0) {
			g.setColor(new Color(0, 255, 0)); //green
		} else if (percentStamina >= 0.7) {
			g.setColor(new Color(154, 205, 50)); //yellow-green
		} else if (percentStamina >= 0.6) {
			g.setColor(new Color(255, 255, 0)); //yellow
		} else if (percentStamina >= 0.4) {
			g.setColor(new Color(255, 165, 0)); //yellow-orange
		} else if (percentStamina >= 0.2) {
			g.setColor(new Color(255, 140, 0)); //orange
		} else {
			g.setColor(new Color(255, 0, 0)); //red
		}

		//fill bar
		g.fillRect(0, Cell.getHeight() * World.HEIGHT, screenWidth * percentStamina, staminaBarHeight);
	}


	public static void increaseDays() {
		days = days + 1;
	}


	public void renderDays(Graphics g) {
		int daysX = 1600;
		int daysY = 35;


		Fonts.big.drawString(daysX + 2, daysY + 2, "Days: " + days, Color.black);

		Fonts.big.drawString(daysX, daysY, "Days: " + days, Color.white);
	}

	public static int getDays() {
		// TODO Auto-generated method stub
		return days;
	}

	public static int getMoney() {
		// TODO Auto-generated method stub
		return money;
	}

	public static ItemBar getItemBar() {
		return itemBar;
	}

	public static int getScore() {
		return score;
	}
}


