package world;

import world.entity.Entity;
import world.entity.Rock;
import world.entity.Sprinkler;
import world.entity.plant.crop.Crop;
import world.entity.plant.Tree.PineTree;
import world.terrain.Dirt;
import world.terrain.Grass;
import world.terrain.Water;
import world.weather.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import item.Item;



public class World {

	public static final int WIDTH = 30;
	public static final int HEIGHT = 15;
	
	private static Cell[][] cells;
	private static ArrayList<Entity> entities;
	
	private static ArrayList<Entity> sprinklers;
    private static ArrayList<Entity> crops;

	public static boolean inBounds(int x, int y) 
	{
		 return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
	}

	public void mousePressed(int button, int x, int y, Item selectedItem) {
        int cellX = x / Cell.getWidth();
        int cellY = y / Cell.getHeight();

        if (inBounds(cellX, cellY)) {

            cells[cellX][cellY].clicked(selectedItem);
        }

    }


	public World() throws SlickException
	{
		
		cells = new Cell[WIDTH][HEIGHT];
        entities = new ArrayList<>();
        
        sprinklers = new ArrayList<>();
        crops = new ArrayList<>();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
		//cells[3][8].setTerrain(new Grass());
		//cells[5][4].setTerrain(new Dirt());
		//cells[5][5].setTerrain(new Dirt());
		readFile();
		
		
		
	}
	
	public static void addEntity(Entity entity, int x, int y) {
        entities.add(entity);

        if (entity instanceof Sprinkler) {
            sprinklers.add(entity);
        } else if (entity instanceof Crop) {
            crops.add(entity);
        }

        if (inBounds(x, y)) {
            cells[x][y].setEntity(entity);
        }
    }


	public void render(Graphics g) {
		for(int i = 0; i < WIDTH; i++)
		{
			for(int j = 0; j < HEIGHT; j++)
			{
				cells[i][j].render(g);
			}
		}
		for (Entity entity : entities) {
            entity.render(g);
        }

		Weather.render();
	}
	
	public void setTerrain(Cell cell, char code) throws SlickException {
		//for the map
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++  ) {
				if (code == ',' ) {
					cell.setTerrain(new Grass());
				} else if (code == '.') {
					cell.setTerrain(new Dirt(false, false));
				} else if (code == 'd') {
					 cell.setTerrain(new Dirt(true, false));
				} else if (code == 'w') {
					 cell.setTerrain(new Water());
				}
			}
		}
	   
	}

	
	
	public void readFile() throws SlickException {
	    try {
	        File mapFile = new File("maps/map1.txt");
	        Scanner scan = new Scanner(mapFile);

	        for (int j = 0; j < HEIGHT; j++) {
	            String row = scan.nextLine();

	            for (int i = 0; i < WIDTH; i++) {
	                char input = row.charAt(i);
	                setTerrain(cells[i][j], input);

	                if (input == 'r') {
	                	//rocks
	                    Rock rock = new Rock();
	                    addEntity(rock, i, j);
	                    cells[i][j].setTerrain(new Dirt(false, false));
	                } else if (input == 't') {
	                    //for pine tree
	                	cells[i][j].setTerrain(new Grass());
	                    PineTree pineTree = new PineTree(5, true);
	                    addEntity(pineTree, i, j);
	                }
	            }
	        }
	        scan.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Cannot find file");
	    }
	}


	public void nextDay() {
		 Weather.nextDay();

	        
	        for (Entity crop : crops) {
	            crop.nextDay();
	        }

	        
	        for (int i = 0; i < WIDTH; i++) {
	            for (int j = 0; j < HEIGHT; j++) {
	                cells[i][j].nextDay();
	            }
	        }

	        
	        for (Entity sprinkler : sprinklers) {
	            sprinkler.nextDay();
	        }
	    }
	
	public void cleanup() {
	    //iterate through entities in reverse order to avoid skipping elements
	    for (int i = entities.size() - 1; i >= 0; i--) {
	        Entity entity = entities.get(i);
	        if (entity.isExpired()) {
	            entities.remove(i);
	        }
	    }
	}
	
	public static Cell getCell(int x, int y) {
	    if (inBounds(x, y)) {
	        return cells[x][y];
	    }
	    return null;
	}


	
}
